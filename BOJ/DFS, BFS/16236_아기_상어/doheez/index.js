'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const N = Number(input[0]);
const map = [];
const babyShark = {};
let feedCnt = 0;
let time = 0;

// 상하좌우
const dRow = [-1, 1, 0, 0];
const dCol = [0, 0, -1, 1];

// map 입력
for (let i = 1; i <= N; i++) {
    map.push(input[i].trim().split(' ').map((str, index) => {
        const num = Number(str);

        if (num === 9) { // 아기 상어의 위치, 크기 저장
            babyShark.row = i - 1;
            babyShark.col = index;
            babyShark.size = 2;
            babyShark.dist = 0;
        }
        return num;
    }));
}
// 아기 상어 자리는 잊지 말고 0으로 초기화해주기
map[babyShark.row][babyShark.col] = 0;

const bfs = (startNode) => {
    const edible = []; // 먹을 수 있는 fish
    const visited = Array.from(Array(N), () => Array(N).fill(false));
    const queue = []; // 지나갈 수 있는 노드
    queue.push(startNode);

    while (queue.length > 0) {
        const node = queue.shift();

        // 검사
        for (let i = 0; i < 4; i++) {
            const newNode = { row: node.row + dRow[i], col: node.col + dCol[i] };
            newNode.dist = node.dist + 1;

            // 범위 안 검사
            if (newNode.row < 0 || newNode.row > N - 1 || newNode.col < 0 || newNode.col > N - 1) {
                continue;
            }
            // 방문 검사
            if (visited[newNode.row][newNode.col] === true) {
                continue;
            }
            // 사이즈 기재
            newNode.size = map[newNode.row][newNode.col];
            // 지나갈 수 있는지 검사
            if (newNode.size > babyShark.size) {
                continue;
            }
            // 모든 조건 만족하면 큐에 넣고 방문
            queue.push(newNode);
            visited[newNode.row][newNode.col] = true;

            // 먹을 수 있으면 저장
            if (newNode.size > 0 && newNode.size < babyShark.size) {
                edible.push(newNode);
            }
        }
    }

    // 먹을 게 있으면
    if (edible.length > 0) {
        let prey;
        if (edible.length === 1) { // 한 마리면 바로 먹이가 되고
            prey = edible[0];
        } else { // 아니면 거리 검사
            let minDist = 1000000;
            let minRow = 1000000;
            let minCol = 1000000;
            let firstCheck = false;
            const candidate = [];

            edible.forEach((fish) => {
                if (fish.dist < minDist) {
                    minDist = fish.dist;
                    prey = fish;
                } else if (fish.dist === minDist) { // 거리조차 같으면 후보로
                    if(!firstCheck){
                        candidate.push(prey);
                    }
                    candidate.push(fish);
                }
            })
            if (candidate.length > 0) { // 우선순위 위쪽, 왼쪽
                candidate.forEach((fish) => {
                    if (fish.row < minRow) {
                        minRow = fish.row;
                        minCol = fish.col;
                        prey = fish;
                    } else if (fish.row === minRow) {
                        if (fish.col < minCol) {
                            minCol = fish.Col;
                            prey = fish;
                        }
                    }
                })
            }
        }
        // 이동하고
        time += prey.dist;
        babyShark.row = prey.row;
        babyShark.col = prey.col;
        // 먹는다
        map[prey.row][prey.col] = 0;
        feedCnt += 1;
        // 사이즈 업
        if (babyShark.size === feedCnt) {
            feedCnt = 0;
            babyShark.size += 1;
        }
        return false;
    }
    // 아니면 종료
    return true;
};

while (1) {
    const callMom = bfs(babyShark);
    
    if (callMom) {
        break;
    }
}

console.log(time);