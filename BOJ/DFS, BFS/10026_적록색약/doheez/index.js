'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');
const N = Number(input[0]);
const painting = [];
const visited = Array.from(Array(N), () => Array(N).fill(false));

for (let i = 1; i <= N; i++) {
    painting.push(input[i].trim());
}

// 상하좌우
const dr = [-1, 1, 0, 0];
const dc = [0, 0, -1, 1];

// 방문한 노드가 있는가 (구역을 발견했는가)
let flag = false;

// 구역 탐색
const dfs = (color, prev, curr) => {
    let same = false; // 같은 색인가

    // 범위 검사
    if (curr.row < 0 || curr.row > N - 1 || curr.col < 0 || curr.col > N - 1) {
        return;
    }
    // 방문 검사
    if (visited[curr.row][curr.col]) {
        return;
    }

    // 같은 색이면 방문
    if (color === 'good') { // 색약 X
        if (painting[prev.row][prev.col] === painting[curr.row][curr.col]) {
            visited[curr.row][curr.col] = true;
            flag = true;
            same = true;
        }
    } else if (color === 'bad') { // 색약 O
        if (painting[prev.row][prev.col] === painting[curr.row][curr.col]
            || (painting[prev.row][prev.col] === 'R' && painting[curr.row][curr.col] === 'G')
            || (painting[prev.row][prev.col] === 'G' && painting[curr.row][curr.col] === 'R')) {
            visited[curr.row][curr.col] = true;
            flag = true;
            same = true;
        }
    }

    // 같은 색이면 네 방향 탐색
    if (same) {
        for (let i = 0; i < 4; i++) {
            const next = { row: curr.row + dr[i], col: curr.col + dc[i] };
            dfs(color, curr, next);
        }
    }
};

const main = () => {
    let goodResult = 0;
    let badResult = 0;

    // good result
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            flag = false;
            dfs('good', { row: i, col: j }, { row: i, col: j });
            if (flag) { // 구역 발견했으면 카운팅
                goodResult++;
            }
        }
    }
    // visited 초기화
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            visited[i][j] = false;
        }
    }
    // bad result
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            flag = false;
            dfs('bad', { row: i, col: j }, { row: i, col: j });
            if (flag) { // 구역 발견했으면 카운팅
                badResult++;
            }
        }
    }
    // print
    console.log(goodResult + ' ' + badResult);
};

main();