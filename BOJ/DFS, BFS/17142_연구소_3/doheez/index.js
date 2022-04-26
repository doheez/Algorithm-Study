'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const EMPTY = 0;
const WALL = 1;
const VIRUS = 2;

const [N, M] = input[0].trim().split(' ').map(str => Number(str));
const lab = [];
const visited = Array.from(Array(N), () => Array(N)); // 방문 및 시간 체크
const location = []; // 바이러스 위치
const locationM = []; // M개의 활성 바이러스 위치
let min = 1000000;
let blank = 0;

for (let i = 1; i <= N; i++) {
    lab.push(input[i].trim().split(' ').map((str, index) => {
        // 입력 받을 때 바이러스의 위치 저장
        if (Number(str) === VIRUS) {
            location.push({ row: i - 1, col: index });
        }
        // 입력 받을 때 빈칸 개수 카운트
        if (Number(str) === EMPTY) {
            blank++;
        }
        return Number(str);
    }));
}

// visited 배열 초기화
const initiateVisited = () => {
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            visited[i][j] = false;
        }
    }
};

// M개 바이러스 활성화
const activateVirus = (m, start) => {
    if (m === M) {
        initiateVisited();
    }

    if (m === 0) {
        spreadVirus();
        return;
    }

    // M개의 바이러스를 활성화시키는 모든 경우의 수
    for (let i = start; i < location.length; i++) {
        locationM.push(location[i]);
        visited[location[i].row][location[i].col] = 0;
        activateVirus(m - 1, i + 1);
        locationM.pop();
    }
};

// 상하좌우
const dr = [-1, 1, 0, 0];
const dc = [0, 0, -1, 1];

// bfs로 바이러스 확산
const spreadVirus = () => {
    // 큐에 활성 바이러스를 먼저 넣어놨으니까
    // 활성 바이러스들로부터 퍼져나가는 시간은 동시에 증가함

    let time = 0; // 확산 시간
    let count = 0; // 바이러스가 퍼진 빈칸 수

    const queue = [...locationM];

    while (queue.length > 0) {
        const node = queue.shift();

        for (let i = 0; i < 4; i++) {
            const newNode = { row: node.row + dr[i], col: node.col + dc[i] };

            // 범위 검사
            if (newNode.row < 0 || newNode.row > N - 1 || newNode.col < 0 || newNode.col > N - 1) {
                continue;
            }

            // 벽이 아니고 방문 안 했으면
            if (lab[newNode.row][newNode.col] !== WALL && !visited[newNode.row][newNode.col]) {

                // 방문하고 큐에 넣음
                visited[newNode.row][newNode.col] = visited[node.row][node.col] + 1;
                queue.push(newNode);

                // 빈칸만 시간 체크
                if (lab[newNode.row][newNode.col] === EMPTY) {
                    time = Math.max(time, visited[newNode.row][newNode.col]);
                    count++;
                }
            }
        }
    }

    // 바이러스가 모두 확산되었으면
    if (count === blank) {
        min = Math.min(time, min);
    } else {
        min = -1;
    }
}

activateVirus(M, 0);
console.log(min);