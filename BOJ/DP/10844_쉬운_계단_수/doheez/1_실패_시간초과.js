'use strict';

// 데이터 입력
const fs = require('fs');
const n = Number(fs.readFileSync(__dirname + '/dev/stdin', 'utf8')); // 1 <= n <= 100

let count = 0;

// 앞 숫자가 정해지면 뒷 숫자 = 앞 숫자 +-1 (단, -1 < 뒷 숫자 < 10)
// 재귀 종료 조건: 정하려는 자릿수가 n-1번째 자릿수일 때 종료 (종료할 때 count++)
const findStepNumber = (curr, nextIndex) => {
    if (nextIndex >= n - 1) {
        if (curr - 1 > -1) count++;
        if (curr + 1 < 10) count++;
    } else {
        if (curr - 1 > -1) findStepNumber(curr - 1, nextIndex + 1);
        if (curr + 1 < 10) findStepNumber(curr + 1, nextIndex + 1);
    }
};

// 제일 첫 자릿수는 0이 올 수 없다.
if (n > 1) {
    for (let i = 1; i <= 9; i++) {
        findStepNumber(i, 1);
    }
} else {
    for (let i = 0; i < 9; i++) {
        count++;
    }
}

count %= 1000000000;
console.log(count);