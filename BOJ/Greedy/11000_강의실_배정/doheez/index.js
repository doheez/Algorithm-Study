'use strict';

const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');
const N = Number(input[0]);
const time = []; // 시작 시각, 종료 시각 이차원 배열

for (let i = 1; i <= N; i++) {
    time.push(input[i].trim().split(' ').map(str => Number(str)));
}

class MinHeap {
    constructor() {
        this.heap = [null];
    }

    size() {
        return this.heap.length - 1;
    }

    getMin() {
        return this.heap[1] ? this.heap[1] : null;
    }

    swap(a, b) {
        [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
    }

    heappush(value) {
        this.heap.push(value);
        let curIdx = this.heap.length - 1;
        let parIdx = (curIdx / 2) >> 0;

        while (curIdx > 1 && this.heap[parIdx] > this.heap[curIdx]) {
            this.swap(parIdx, curIdx)
            curIdx = parIdx;
            parIdx = (curIdx / 2) >> 0;
        }
    }

    heappop() {
        const min = this.heap[1];
        if (this.heap.length <= 2) this.heap = [null];
        else this.heap[1] = this.heap.pop();

        let curIdx = 1;
        let leftIdx = curIdx * 2;
        let rightIdx = curIdx * 2 + 1;

        if (!this.heap[leftIdx]) return min;
        if (!this.heap[rightIdx]) {
            if (this.heap[leftIdx] < this.heap[curIdx]) {
                this.swap(leftIdx, curIdx);
            }
            return min;
        }

        while (this.heap[leftIdx] < this.heap[curIdx] || this.heap[rightIdx] < this.heap[curIdx]) {
            const minIdx = this.heap[leftIdx] > this.heap[rightIdx] ? rightIdx : leftIdx;
            this.swap(minIdx, curIdx);
            curIdx = minIdx;
            leftIdx = curIdx * 2;
            rightIdx = curIdx * 2 + 1;
        }

        return min;
    }
}

const room = new MinHeap(); // 종료 시각 우선순위 큐

const findRooms = () => {
    // 시작 시간이 빠른 순으로 정렬
    time.sort((a, b) => ( a[0] - b[0] ));

    // 우선순위 큐를 사용해 강의실 배정. 각 강의실엔 가장 늦게 끝나는 수업의 종료 시각만 저장.
    room.heappush(time[0][1]);

    for (let i = 1; i < N; i++) {
        // 넣을 수업의 시작 시각이 강의실의 종료 시각보다 작으면 해당 강의실 사용 가능
        if (room.getMin() <= time[i][0]) {
            room.heappop();
            room.heappush(time[i][1]);
        } else { // 아니면 새로운 강의실 개설
            room.heappush(time[i][1]);
        }
    }
};

findRooms();
console.log(room.size());