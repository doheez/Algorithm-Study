'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const N = Number(input[0]);
const sequence = input[1].split(' ').map(str => Number(str));

const findLIS4 = () => {
    // dp[i]: sequence[i]를 마지막 원소로 가지는 LIS 길이
    const dp = Array(N).fill(1);

    let max = 0;

    for (let i = 1; i < N; i++) {
        for (let j = 0; j < i; j++) {
            if (sequence[j] < sequence[i] && dp[j] > max) {
                max = dp[j];
            }
        }
        dp[i] = max + 1;
        max = 0;
    }

    // max: LIS 길이
    // maxIdx: LIS의 마지막 index
    max = 1;
    let maxIdx = 0;
    for (let i = 0; i < N; i++) {
        if (dp[i] > max) {
            max = dp[i];
            maxIdx = i;
        }
    }

    console.log(max);

    // LIS가 어떻게 만들어졌는지 마지막 index로부터 역추적
    // LIS에서 i번째 원소는 i+1번째 원소보다 dp값이 1 작다는 점을 이용
    const print = (max, maxIdx) => {
        if(maxIdx < 0){
            return;
        }
        if(dp[maxIdx] === max){
            print(--max, --maxIdx);
            process.stdout.write(`${sequence[++maxIdx]} `);
        } else {
            print(max, --maxIdx);
        }
    };

    print(max, maxIdx);

    // 다른 출력법

    // const LIS = [];
    // while(maxIdx >= 0){
    //     if(dp[maxIdx] === max){
    //         LIS.push(sequence[maxIdx]);
    //         max--;
    //     }
    //     maxIdx--;
    // }

    // for(let i = LIS.length - 1; i >= 0; i--){
    //     process.stdout.write(`${LIS[i]} `);
    // }
};

findLIS4();