'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const [N, K] = input[0].split(' ').map(str => Number(str)); // 물품의 수 N(1 ≤ N ≤ 100), 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)
const W = [0]; // 물건의 무게 W(1 ≤ W ≤ 100,000), index 1부터 시작
const V = [0]; // 물건의 가치 V(0 ≤ V ≤ 1,000), index 1부터 시작

for (let i = 1; i <= N; i++) {
    const [w, v] = input[i].trim().split(' ');
    W.push(Number(w));
    V.push(Number(v));
}

const Knapsack = () => {
    // dp[i][w]: 물건의 개수가 i개, 최대로 담을 수 있는 무게가 w일 때의 최적 가치 v
    const dp = Array.from(Array(N + 1), () => Array(K + 1).fill(0));

    for (let i = 1; i <= N; i++) {
        for (let w = 1; w <= K; w++) {
            if (W[i] > w) {
                dp[i][w] = dp[i - 1][w];
            } else {
                dp[i][w] = Math.max(dp[i - 1][w - W[i]] + V[i], dp[i - 1][w]);
            }
        }
    }
    console.log(dp[N][K]);
};

Knapsack();