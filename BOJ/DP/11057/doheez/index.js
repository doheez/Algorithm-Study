'use strict';

// 데이터 입력
const fs = require('fs');
const N = Number(fs.readFileSync(__dirname + '/dev/stdin', 'utf8')); // 1 <= n <= 1000

const findAscdNumber = () => {
    const dp = Array.from(Array(N + 1), () => Array(10).fill(0));

    // N이 1일 때의 오르막 수 개수
    for (let j = 0; j <= 9; j++) {
        dp[1][j] = 1;
    }

    // dp[i][j]: N==i이고 1의 자릿수가 j인 오르막 수의 개수
    // dp[i-1][0]부터 dp[i-1][j]까지 더한 것
    for (let i = 2; i <= N; i++) {
        for (let j = 0; j <= 9; j++) {
            for (let k = 0; k <= j; k++) {
                dp[i][j] += dp[i - 1][k] % 10007;
            }
        }
    }

    const result = dp[N].reduce((acc, cur) => { return (acc + cur) % 10007 }, 0);
    console.log(result);
};

findAscdNumber();