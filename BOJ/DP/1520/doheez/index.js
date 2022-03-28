'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const [M, N] = input[0].split(' ').map(str => Number(str)); // 1 <= M, N <= 500
const arr = []; // 1 <= 각 지점의 높이 <= 10000
for (let i = 1; i <= M; i++) {
    arr.push(input[i].split(' ').map(str => Number(str)));
}

// dp[i][j]: arr[i][j]에서 arr[M-1][N-1]까지 도달할 수 있는 경로의 수
// 방문 여부를 표시하기 위해 -1로 초기화 (0으로 하면 방문 여부 알 수 X)
const dp = Array.from(Array(M), () => Array(N).fill(-1));

const dfs = (i, j) => {
    if (i === M - 1 && j === N - 1) { // 끝에 도달했으면
        dp[i][j] = 1;
        return dp[i][j];
    }

    if (dp[i][j] === -1) { // 방문하지 않았으면
        dp[i][j] = 0;
        if (i - 1 > -1 && arr[i - 1][j] < arr[i][j]) { // 상
            dp[i][j] += dfs(i - 1, j);
        }
        if (i + 1 < M && arr[i + 1][j] < arr[i][j]) { // 하
            dp[i][j] += dfs(i + 1, j);
        }
        if (j - 1 > -1 && arr[i][j - 1] < arr[i][j]) { // 좌
            dp[i][j] += dfs(i, j - 1);
        }
        if (j + 1 < N && arr[i][j + 1] < arr[i][j]) { // 우
            dp[i][j] += dfs(i, j + 1);
        }
    }

    return dp[i][j];
};

dfs(0, 0);
console.log(dp[0][0]);