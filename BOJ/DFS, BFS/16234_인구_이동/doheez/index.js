'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const [N, L, R] = input[0].trim().split(' ').map(str => Number(str));
const A = []; // countries
const visited = Array.from(Array(N), () => Array(N).fill(false));
let dayCnt = 0;

for (let i = 1; i <= N; i++) {
    A.push(input[i].trim().split(' ').map(str => Number(str)));
}

// 상하좌우
const dr = [-1, 1, 0, 0];
const dc = [0, 0, -1, 1];

// bfs로 연합 검사
const bfs = (startNode) => {
    const queue = [];
    const union = []; // 연합

    visited[startNode.row][startNode.col] = true;
    queue.push(startNode);

    while (queue.length > 0) {
        const node = queue.shift();

        for (let i = 0; i < 4; i++) {
            const newNode = { row: node.row + dr[i], col: node.col + dc[i] };

            // 범위 검사
            if (newNode.row < 0 || newNode.row > N - 1 || newNode.col < 0 || newNode.col > N - 1) {
                continue;
            }

            // 방문 검사
            if (visited[newNode.row][newNode.col]) {
                continue;
            }

            // 연합 조건 검사
            const diff = Math.abs(A[node.row][node.col] - A[newNode.row][newNode.col]);
            if (diff < L || diff > R) {
                continue;
            }

            // 연합이면 방문
            visited[newNode.row][newNode.col] = true;

            // 연합이면 큐랑 연합에 추가
            if (!union.includes(node)) {
                union.push(node);
            }
            queue.push(newNode);
            union.push(newNode);
        }
    }

    return union;
};

// 인구 이동
const move = () => {
    const allUnions = [];

    // 배열 전체에 대해 bfs하며 해당 day에 존재하는 모든 연합을 구함
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            const union = bfs({ row: i, col: j });

            if (union.length !== 0) {
                allUnions.push(union);
            }
        }
    }

    // visited 초기화
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            visited[i][j] = false;
        }
    }

    // 연합 각각이 하루 동안 인구 이동
    if (allUnions.length > 0) {
        allUnions.forEach((union) => {
            if (union.length > 0) {
                const sum = union.reduce((prev, curr) => prev + A[curr.row][curr.col], 0);
                const result = Math.floor(sum / union.length);

                // 연합을 이루고 있는 각 칸의 인구수 = (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
                while (union.length !== 0) {
                    const node = union.pop();
                    A[node.row][node.col] = result;
                }
            }
        });
        dayCnt++;
        return false;
    } else {
        return true;
    }
};

let stop = false;
while (!stop) {
    stop = move();
}

console.log(dayCnt);