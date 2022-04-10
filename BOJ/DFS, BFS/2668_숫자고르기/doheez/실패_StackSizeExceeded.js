'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n').map(str => Number(str));

const N = input[0];
// 첫째 줄: input 배열의 index (0 제외)
// 둘째 줄: input[1] ~ input[N]
const result = []; // 집합

// 집합 찾기 (사이클 찾기)
const dfs = (cur, start) => {

    if (input[cur] === 0) { // 이미 집합에 있거나 가본 곳
        return false;
    } else if (input[cur] === start) { // input[cur]이 start와 같다면 집합 완성
        result.push(cur);
        input[cur] = 0;
        return true;
    } else {
        if (cur === input[cur]) { // 그 자체로 cycle
            result.push(cur);
            input[cur] = 0;
            return false;
        }
        // include === true: start로 가는 중간 다리
        // include === false: cycle이 아님
        const include = dfs(input[cur], start);
        if (include) {
            result.push(cur);
        }
        input[cur] = 0;
    }
};

for (let i = 1; i <= N; i++) {
    if (input[i] !== 0) {
        dfs(i, i);
    }
}

result.sort((a, b) => a - b);
console.log(result.length);
result.forEach(e => console.log(e));