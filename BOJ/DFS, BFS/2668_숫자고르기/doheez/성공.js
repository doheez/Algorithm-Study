'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n').map(str => Number(str));

const N = input[0];
// 첫째 줄: input 배열의 index (0 제외)
// 둘째 줄: input[1] ~ input[N]
const result = []; // 집합
const visited = Array(N + 1).fill(false);

// 집합 찾기 (사이클 찾기)
const dfs = (cur, start) => {
    if (visited[cur] === false) { // 방문 안 했으면 가자
        visited[cur] = true;
        dfs(input[cur], start);
    } else if (cur === start) { // 이미 방문한 곳인데 처음으로 돌아오면 사이클
        result.push(start);
        return;
    }
};

for (let i = 1; i <= N; i++) {
    if (input[i] !== 0) {
        visited.fill(false);
        dfs(i, i);
    }
}

result.sort((a, b) => a - b);
console.log(result.length);
result.forEach(e => console.log(e));