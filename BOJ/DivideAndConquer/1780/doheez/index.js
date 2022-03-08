'use strict';

// 데이터 입력
const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const n = +input[0];
let data = [];

for (let i = 1; i < n + 1; i++) {
    data.push(input[i].split(' ').map((str) => +str));
}

// count[0]: -1로만 채워진 종이의 개수
// count[1]: 0으로만 채워진 종이의 개수
// count[2]: 1로만 채워진 종이의 개수
let count = [0, 0, 0];

const slicePaper = (paper, n) => {
    const firstNum = n === 1 ? paper : paper[0][0];
    let checkSamePaper = true;

    // 종이가 모두 같은 수로 이루어져 있는지 확인
    if (n !== 1) {
        outer: for (let i = 0; i < n; i++) {
            inner: for (let j = 0; j < n; j++) {
                if (firstNum !== paper[i][j]) {
                    checkSamePaper = false;
                    break outer;
                }
            }
        }
    }

    if (checkSamePaper === true) {
        count[firstNum + 1]++;
        return;
    } else { // 아니면 종이 자르기
        const newN = n / 3;
        let newPaper = []; // 2차원 배열 종이
        let newOnePaper; // 숫자 한 개짜리 종이

        for (let i = 0; i < 3; i++) {
            for (let j = 0; j < 3; j++) {
                if (newN !== 1) {
                    for (let k = 0; k < newN; k++) {
                        newPaper.push(paper[i * newN + k].slice(j * newN, (j + 1) * newN));
                    }
                    slicePaper(newPaper, newN);
                    newPaper = [];
                } else {
                    newOnePaper = paper[i][j];
                    slicePaper(newOnePaper, newN);
                }
            }
        }
    }
};

slicePaper(data, n);

count.forEach((cnt) => console.log(cnt));