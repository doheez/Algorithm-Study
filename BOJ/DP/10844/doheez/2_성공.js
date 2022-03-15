'use strict';

// 데이터 입력
const fs = require('fs');
const N = Number(fs.readFileSync(__dirname + '/dev/stdin', 'utf8')); // 1 <= n <= 100

const findStepNumber = () => {
    const dp = Array.from(Array(N + 1), () => Array(10).fill(0));

    // 0은 계단 수가 아니다.
    for (let j = 1; j <= 9; j++) {
        dp[1][j] = 1;
    }

    // dp[N][j] = dp[N-1][j-1] + dp[N-1][j+1]
    // 길이가 N이고 마지막 자릿수가 j인 계단 수의 개수
    for (let i = 2; i <= N; i++) {
        dp[i][0] = dp[i - 1][1];

        for (let j = 1; j <= 8; j++) {
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1] % 1000000000;
        }

        dp[i][9] = dp[i - 1][8];
    }
    const result = dp[N].reduce((acc, cur) => { return (acc + cur) % 1000000000 }, 0);
    console.log(result);
};

findStepNumber();