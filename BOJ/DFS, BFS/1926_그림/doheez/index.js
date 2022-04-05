const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');

const [n, m] = input[0].split(' ').map(str => Number(str));
const dohwaji = [];
for (let i = 1; i <= n; i++) {
    dohwaji.push(input[i].split(' ').map(str => Number(str)));
}

let grimCnt = 0; // 그림의 개수
const grimSizeList = [0]; // 그림들의 넓이. 그림이 하나도 없는 경우 가장 넓은 그림의 넓이는 0이다.

const bfs = (node) => {
    const queue = [];
    const dx = [-1, 1, 0, 0];
    const dy = [0, 0, -1, 1];
    let grimSize = 0;

    queue.push(node);

    while (queue.length !== 0) {
        node = queue.shift();

        if (dohwaji[node.x][node.y] === 1) { // 방문 안 했으면
            dohwaji[node.x][node.y] = 0; // 방문했다고 표시
            grimSize++;

            for (let i = 0; i < 4; i++) { // 노드의 상하좌우 탐색해서 그림 조각만(1인 것만) 큐에 넣음
                const newNode = { x: node.x + dx[i], y: node.y + dy[i] };
                if (newNode.x >= 0 && newNode.x <= n - 1 && newNode.y >= 0 && newNode.y <= m - 1) {
                    if (dohwaji[newNode.x][newNode.y] === 1) {
                        queue.push(newNode);
                    }
                }
            }
        }
    }
    grimSizeList.push(grimSize);
};

const findGrims = () => {
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (dohwaji[i][j] === 1) {
                bfs({ x: i, y: j });
                grimCnt++;
            }
        }
    }
};

findGrims();
console.log(grimCnt);
console.log(Math.max(...grimSizeList));