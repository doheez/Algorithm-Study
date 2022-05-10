'use strict';

const fs = require('fs');
const { exit } = require('process');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const N = Number(input[0]);

// ABCDEF (A-F, B-E, C-D는 서로 마주보는 면이고, 각 면의 index 합은 5임)
const dice = input[1].trim().split(' ').map(str => Number(str));

// 주사위에 쓰여있는 수 <= 50
let min3 = 150;
let min2 = 100;
let min1 = 50;

const findMin = (n, start, depth, selected) => {
    // n개 다 골랐으면
    if (depth === n + 1) {
        if (n === 2) {
            min2 = Math.min(min2, selected.reduce((prev, curr) => (prev + dice[curr]), 0));
        } else if (n === 3) {
            min3 = Math.min(min3, selected.reduce((prev, curr) => (prev + dice[curr]), 0));
        }
    }
    for (let i = start; i < 6; i++) {
        let opposite = false; // 반대쪽 면인가

        // 이미 고른건 start에서 걸러줌, 고른 것의 반대편을 피해야 함
        for (let j = 0; j < selected.length; j++) {
            if (i + selected[j] === 5) {
                opposite = true;
            }
        }
        if (opposite) {
            continue;
        }
        // 골랐던 것의 반대편이 아니라면
        selected.push(i);
        findMin(n, i + 1, depth + 1, selected);
        selected.pop();
    }
};

const main = () => {
    let result = 0;

    // N이 1인 경우만 따로 처리
    if (N === 1) {
        let sum = dice.reduce((prev, curr) => (prev + curr), 0);
        let max = Math.max(...dice);
        let resultN1 = sum - max;
        console.log(resultN1);
        exit(0);
    }

    // 세 면이 보이는 주사위: 4개
    result += min3 * 4;

    // 두 면이 보이는 주사위: (n-1)*4 + (n-2)*4개
    result += min2 * ((N - 1) * 4 + (N - 2) * 4);

    // 한 면이 보이는 주사위: (n-2)^2 + (n-2)*(n-1)*4개
    result += min1 * ((N - 2) * (N - 2) + (N - 1) * (N - 2) * 4);

    console.log(result);
};

findMin(3, 0, 1, []);
findMin(2, 0, 1, []);
dice.forEach((e) => {
    min1 = Math.min(min1, e);
});
main();