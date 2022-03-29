// 데이터 입력
const fs = require('fs');
const input = fs.readFileSync(__dirname + '/dev/stdin', 'utf8').split('\n');
const N = Number(input[0]); // 수열 A의 크기 N (1 ≤ N ≤ 1,000)
const sequence = input[1].split(' ').map(e => Number(e)); // 수열 A를 이루고 있는 Ai (1 ≤ Ai ≤ 1,000)

const findAscdSeq = () => {
    // dp 배열 원소값: 자기보다 작은 index 중에서 가장 큰 dp[index] + 1
    // 자기보다 작은 index가 없을 경우 부분 수열에 자기만 포함하니까 모두 1로 초기화
    const dp = Array(N).fill(1);

    let max = 0;

    for (let i = 1; i < N; i++) {
        for (let j = 0; j < i; j++) {
            if (sequence[j] < sequence[i] && dp[j] > max) {
                max = dp[j];
            }
        }
        dp[i] = max + 1;
        max = 0;
    }

    max = 1;
    for (let i = 0; i < N; i++) {
        if (dp[i] > max) {
            max = dp[i];
        }
    }

    console.log(max);
};

findAscdSeq();