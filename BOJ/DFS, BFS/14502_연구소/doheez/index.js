const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const map = [];
const location = [];

const EMPTY = 0;
const WALL = 1;
const VIRUS = 2;

const [N, M] = input[0].split(' ').map(str => Number(str));

for (let i = 1; i <= N; i++) {
    map.push(input[i].trim().split(' ').map((str, index) => {
        // 입력 받을 때 바이러스의 위치를 저장
        if (Number(str) === VIRUS) {
            location.push({ x: i - 1, y: index });
        }
        return Number(str);
    }));
}

let max = 0;

const checkSafetyZone = () => {
    let emptyCnt = 0;

    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            // 안전 영역이면 세고
            if (map[i][j] === EMPTY) {
                emptyCnt++;
            }
            // 바이러스 있으면 비워주기
            if (map[i][j] === VIRUS) {
                map[i][j] = EMPTY;
            }
        }
    }
    // 바이러스가 원래부터 있던 곳은 복원
    location.forEach((node) => {
        map[node.x][node.y] = VIRUS;
    });

    max = Math.max(emptyCnt, max);
};

// 상하좌우
const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];

// BFS로 바이러스 확산시키기
const spreadVirus = () => {
    const queue = [...location];

    while (queue.length !== 0) {
        const node = queue.shift();

        for (let i = 0; i < 4; i++) {
            const newNode = { x: node.x + dx[i], y: node.y + dy[i] };

            if (newNode.x > -1 && newNode.x < N && newNode.y > -1 && newNode.y < M) { // 범위 내
                if (map[newNode.x][newNode.y] === EMPTY && !queue.includes(newNode)) { // 빈칸이고 큐에 없다면
                    queue.push(newNode); // 큐에 넣고
                    map[newNode.x][newNode.y] = VIRUS; // 바이러스 확산
                }
            }
        }
    }
    checkSafetyZone();
};

const createWall = (cnt) => {
    if (cnt === 3) {
        spreadVirus();
        return;
    }

    // 벽을 세울 수 있는 모든 경우의 수
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            if (map[i][j] === EMPTY) {
                map[i][j] = WALL;
                createWall(cnt + 1);
                // 벽 3개를 세우고 바이러스 확산 후 안전 영역 수를 셌다면 벽을 곧바로 허문다
                map[i][j] = EMPTY;
            }
        }
    }
};

createWall(0);
console.log(max);