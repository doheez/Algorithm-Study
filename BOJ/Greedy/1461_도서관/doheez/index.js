'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');
const [N, M] = input[0].trim().split(' ').map(str => Number(str)); // N, M <= 50
const books = input[1].trim().split(' ').map(str => Number(str)); // |책 위치| <= 10,000

const work = () => {
    let divider = N;
    let steps = 0;

    // 오름차순 정렬
    books.sort((a, b) => (a - b));

    // 처음 양수가 되는 index 찾기
    for (let i = 0; i < N; i++) {
        if (books[i] > 0) {
            divider = i;
            break;
        }
    }

    // 음수 책 갖다놓기
    for (let i = 0; i < divider; i += M) {
        steps += 2 * Math.abs(books[i]);
    }

    // 양수 책 갖다놓기
    for (let i = N - 1; i >= divider; i -= M) {
        steps += 2 * books[i];
    }

    // 마지막 책은 갖다놓고 안 돌아와도 되니까 가장 큰 값 빼기
    steps -= Math.max(Math.abs(books[N - 1]), Math.abs(books[0]));
    console.log(steps);
};

work();