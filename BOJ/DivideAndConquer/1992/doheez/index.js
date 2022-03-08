'use strict';

// 데이터 입력
const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const n = +input[0];
const data = [];

for (let i = 1; i < n + 1; i++) {
    data.push(input[i].trim().split('').map((str) => +str));
}

const condense = (video, n, start, end) => {
    const firstNum = n === 1 ? video : video[0][0];
    let checkSameVideo = true;

    if (start) {
        process.stdout.write('(');
    }

    // 영상이 모두 같은 수로만 이루어져 있는지 검사
    if (n !== 1) {
        outer: for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                if (firstNum !== video[i][j]) {
                    checkSameVideo = false;
                    break outer;
                }
            }
        }
    }

    if (checkSameVideo) {
        process.stdout.write(`${firstNum}`);
    } else { // 아니면 4개의 영상으로 자르기
        const newN = n / 2;
        let newVideo = [];
        let start = false;
        let end = false;

        for (let i = 0; i < 2; i++) {
            for (let j = 0; j < 2; j++) {
                // 왼쪽 위, 오른쪽 아래 영상만 괄호로 묶기
                if (i === 0 && j === 0) {
                    start = true;
                } else if (i === 1 && j === 1) {
                    end = true;
                }

                // 잘린 영상이 2차원 배열일 경우와 아닌 경우를 나누어서 자르기 진행
                if (newN === 1) {
                    condense(video[i][j], newN, start, end);
                } else {
                    for (let k = 0; k < newN; k++) {
                        newVideo.push(video[i * newN + k].slice(j * newN, (j + 1) * newN));
                    }
                    condense(newVideo, newN, start, end);
                    newVideo = [];
                }
                start = false;
                end = false;
            }
        }
    }
    
    if (end) {
        process.stdout.write(')');
    }
};

condense(data, n, false, false);