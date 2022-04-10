'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const n = Number(input[0]);
const node = Array.from(Array(n + 1), () => []);

// (n - 1)개의 간선을 입력으로 받아 각 노드 연결
for (let i = 1; i <= n - 1; i++) {
    const [parent, child, weight] = input[i].trim().split(' ').map(str => Number(str));

    node[parent].push({ v: child, w: weight });
    node[child].push({ v: parent, w: weight });
}

const visited = new Array(n + 1).fill(false);
let max = 0;
let end = 0;

const dfs = (v, w) => {
    // 방문 체크
    if (visited[v]) {
        return;
    }
    // 방문
    visited[v] = true;
    if (max < w) {
        max = w;
        end = v;
    }

    // 연결된 노드 dfs
    node[v].forEach((nextNode) => {
        dfs(nextNode.v, w + nextNode.w);
    })
};

// 아무 점(여기서는 1)에서 가장 멀리 떨어진 점 end 구하기
dfs(1, 0);
max = 0;
visited.fill(false);

// end에서 가장 멀리 떨어진 점과의 거리 구하기
dfs(end, 0);

console.log(max);