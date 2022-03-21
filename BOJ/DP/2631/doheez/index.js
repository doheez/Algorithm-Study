'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n').map(str => Number(str));

const N = input[0];
const children = input.slice(1, input.length);

const findLIS = () => {
    const dp = Array(N).fill(1);
    let max = 0;

    for (let i = 1; i < N; i++) {
        for (let j = 0; j < i; j++) {
            if (children[i] > children[j] && dp[j] > max) {
                max = dp[j];
            }
        }
        dp[i] = max + 1;
        max = 0;
    }

    for (let i = 0; i < N; i++) {
        if (dp[i] > max) {
            max = dp[i];
        }
    }

    return max;
};

// LIS를 구성하지 않는 아이들만 재배치하면 됨
const result = N - findLIS();
console.log(result);