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

const MIN_INIT = 1000000;
let min = MIN_INIT;
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
        visited[location[i].row][location[i].col] = 1;
        activateVirus(m - 1, i + 1);
        locationM.pop();
        visited[location[i].row][location[i].col] = false;
    }
};

// 상하좌우
const dr = [-1, 1, 0, 0];
const dc = [0, 0, -1, 1];

// bfs로 바이러스 확산
const spreadVirus = () => {
    // 큐에 활성 바이러스를 먼저 넣어놨으니까
    // 활성 바이러스들로부터 퍼져나가는 시간은 동시에 증가함

    let count = 0; // 바이러스가 퍼진 빈칸 수

    const queue = [...locationM];

    // visited를 바로 쓰면 오염되므로 새 visit 배열 만듦
    const visit = Array.from(Array(N), () => Array(N));
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            visit[i][j] = visited[i][j];
        }
    }
    while (queue.length > 0) {
        const node = queue.shift();

        for (let i = 0; i < 4; i++) {
            const newNode = { row: node.row + dr[i], col: node.col + dc[i] };

            // 범위 검사
            if (newNode.row < 0 || newNode.row > N - 1 || newNode.col < 0 || newNode.col > N - 1) {
                continue;
            }

            // 벽이 아니고 방문 안 했으면
            if (lab[newNode.row][newNode.col] !== WALL && visit[newNode.row][newNode.col] === false) {

                // 빈칸만 개수 세기
                if (lab[newNode.row][newNode.col] === EMPTY) {
                    count++;
                }
                // 바이러스가 모두 확산되었으면 종료
                if (count === blank) {
                    min = Math.min(visit[node.row][node.col], min);
                    return;
                }
                // 방문하고 큐에 넣음
                visit[newNode.row][newNode.col] = visit[node.row][node.col] + 1;
                queue.push(newNode);
            }
        }
    }
}

const main = () => {
    // 빈칸이 처음부터 없으면 곧바로 종료
    if (blank == 0) {
        console.log(0)
        return;
    }
    // 바이러스 확산
    activateVirus(M, 0);

    // min이 초기값과 같으면 바이러스 확산 실패
    if (min === MIN_INIT) {
        min = -1;
    }
    // 출력
    console.log(min);
};

main();