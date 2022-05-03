'use strict';

const fs = require('fs');
const { exit } = require('process');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const [N, M] = input[0].trim().split(' ').map(str => Number(str));
const icebergOrig = Array.from(Array(N), () => Array(M).fill(0)); // 바닷물 탐색할 빙산
const icebergTest = Array.from(Array(N), () => Array(M).fill(0)); // 실제로 녹일 빙산
const visited = Array.from(Array(N), () => Array(M).fill(false));
let findIceberg = false; // 새로운 빙산을 찾았는가

for (let i = 1; i <= N; i++) {
    input[i].trim().split(' ').map((str, index) => {
        const ice = Number(str);
        icebergOrig[i - 1][index] = ice;
        icebergTest[i - 1][index] = ice;
        return ice;
    })
}

// 상하좌우
const dr = [-1, 1, 0, 0];
const dc = [0, 0, -1, 1];

const melt = () => {
    // 빙산 녹이기
    for (let i = 1; i < N - 1; i++) {
        for (let j = 1; j < M - 1; j++) {
            // 빙산이라면
            if (icebergOrig[i][j] !== 0) {
                // 상하좌우 탐색해서 바닷물과 접한 만큼 녹이기
                for (let k = 0; k < 4; k++) {
                    if (icebergOrig[i + dr[k]][j + dc[k]] === 0) {
                        icebergTest[i][j]--;
                    }
                }
                // 음수가 되면 0으로 바꾸기
                if (icebergTest[i][j] < 0) {
                    icebergTest[i][j] = 0;
                }
            }
        }
    }
    for (let i = 1; i < N - 1; i++) {
        for (let j = 1; j < M - 1; j++) {
            icebergOrig[i][j] = icebergTest[i][j];
        }
    }
};

const dfs = (node) => {
    // 범위 검사
    if (node.row < 1 || node.row > N - 2 || node.col < 1 || node.col > M - 2) {
        return;
    }

    // 방문 검사
    if (visited[node.row][node.col]) {
        return;
    }

    // 빙산이면 방문
    if (icebergOrig[node.row][node.col] !== 0) {
        visited[node.row][node.col] = true;
        findIceberg = true;

        // 이동
        for (let i = 0; i < 4; i++) {
            dfs({ row: node.row + dr[i], col: node.col + dc[i] });
        }
    }
};

const main = () => {
    let time = 0; // 빙산이 두 덩어리 이상으로 분리되는 데 걸리는 시간
    

    while (1) {
        let icebergCnt = 0; // 빙산 개수

        // 빙산이 모두 녹았는데도 분리되지 않으면
        let zeroCnt = 0;
        for (let i = 1; i < N - 1; i++) {
            for (let j = 1; j < M - 1; j++) {
                if (icebergOrig[i][j] === 0) {
                    zeroCnt++;
                }
            }
        }
        // 0 출력하고 종료
        if (zeroCnt === (N - 2) * (M - 2)) {
            console.log(0);
            exit(0);
        }

        // 빙산 녹이고
        melt();
        time++;

        // 빙산이 분리될 때까지 dfs 탐색
        for (let i = 1; i < N - 1; i++) {
            for (let j = 1; j < M - 1; j++) {
                findIceberg = false;
                dfs({ row: i, col: j });

                // 빙산을 2개 이상 발견하면 시간 출력하고 종료
                if (findIceberg && ++icebergCnt >= 2) {
                    console.log(time);
                    exit(0);
                }
            }
        }
        // visited 초기화
        for (let i = 1; i < N - 1; i++) {
            for (let j = 1; j < M - 1; j++) {
                visited[i][j] = false;
            }
        }
    }
};

main();