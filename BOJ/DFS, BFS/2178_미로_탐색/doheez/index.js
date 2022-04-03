const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const [N, M] = input[0].split(' ').map(str => Number(str));
const maze = [];

for (let i = 1; i <= N; i++) {
    maze.push(input[i].trim().split('').map(str => Number(str)));
}

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

const bfs = (node) => {
    const count = Array.from(Array(N), () => Array(M).fill(0)); // 시작점으로부터의 거리 표시
    const queue = [];

    queue.push(node); // 시작점 넣기

    outer: while (queue.length !== 0) {
        node = queue.shift();

        // 상하좌우 탐색
        for (let i = 0; i < 4; i++) {
            const newNode = { x: node.x + dx[i], y: node.y + dy[i] };

            if (newNode.x > -1 && newNode.x < N && newNode.y > -1 && newNode.y < M) {
                if (maze[newNode.x][newNode.y] === 1 && !queue.includes(newNode)) {
                    queue.push(newNode);
                    maze[newNode.x][newNode.y] = 0;
                    count[newNode.x][newNode.y] = count[node.x][node.y] + 1;

                    // 끝에 도착하면 멈춰
                    if (newNode.x === N - 1 && newNode.y === M - 1) {
                        break outer;
                    }
                }
            }
        }
    }
    console.log(count[N - 1][M - 1] + 1);
};

bfs({ x: 0, y: 0 });