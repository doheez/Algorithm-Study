'use strict';

const { exit } = require('process');
const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');
const [N, M] = input[0].trim().split(' ').map(str => Number(str));

const friends = Array(N);
for (let i = 0; i < N; i++) {
    friends[i] = [];
}

const visited = Array(N).fill(false);

for (let i = 1; i <= M; i++) {
    const [p1, p2] = input[i].trim().split(' ').map(str => Number(str));

    friends[p1].push(p2);
    friends[p2].push(p1);
}

const dfs = (p, depth) => {
    // 방문 표시
    visited[p] = true;

    // ABCDE 만족
    if (depth === 4) {
        console.log(1);
        exit(0);
    }
    // friends[p]의 친구로 이동
    for (let i = 0; i < friends[p].length; i++) {
        if (!visited[friends[p][i]]) { // 방문하지 않았으면
            dfs(friends[p][i], depth + 1);
        }
    }
    // 방문 표시 해제
    visited[p] = false;
};

const main = () => {
    // 모든 사람들에 대해 dfs
    for (let i = 0; i < N; i++) {
        dfs(i, 0);
    }
    console.log(0);
};

main();