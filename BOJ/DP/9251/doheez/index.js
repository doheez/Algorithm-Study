'use strict';

const fs = require('fs');
const sequence = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n').map(str => str.trim());

const findLCS = () => {
    const N1 = sequence[0].length;
    const N2 = sequence[1].length;
    const dp = Array.from(Array(N1 + 1), () => Array(N2 + 1).fill(0));

    for (let i = 1; i <= N1; i++) {
        for (let j = 1; j <= N2; j++) {
            if (sequence[0][i - 1] === sequence[1][j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }
    console.log(dp[N1][N2]);
};

findLCS();