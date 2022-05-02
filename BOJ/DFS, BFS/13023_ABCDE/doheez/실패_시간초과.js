'use strict';

const { exit } = require('process');
const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');
const [N, M] = input[0].trim().split(' ').map(str => Number(str));

const relations = []; // M개의 친구 관계 배열
const friends = Array.from(Array(N), () => Array(N).fill(false)); // 'friends[p1][p2] === true'는 p1과 p2가 친구라는 뜻
const visited = Array(N).fill(false);

for (let i = 1; i <= M; i++) {
    const [p1, p2] = input[i].trim().split(' ').map(str => Number(str));

    relations.push({ p1: p1, p2: p2 });
    friends[p1][p2] = true;
    friends[p2][p1] = true;
}

const dfs = (relation, depth) => {
    // 방문 표시
    visited[relation.p1] = true;

    // ABCDE 만족
    if (depth === 4) {
        console.log(1);
        exit(0);
    }
    // relation.p2의 친구로 이동
    for (let i = 0; i < N; i++) {
        if (friends[relation.p2][i] && !visited[i]) { // 친구고 방문하지 않았으면
            dfs({ p1: relation.p2, p2: i }, depth + 1);
        }
    }
    // 방문 표시 해제
    visited[relation.p1] = false;
};

const main = () => {
    // 모든 관계들에 대해 dfs
    for (let i = 0; i < M; i++) {
        dfs(relations[i], 1);
    }
    console.log(0);
};

main();