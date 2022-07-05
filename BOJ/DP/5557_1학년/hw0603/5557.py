import sys

N = int(sys.stdin.readline())
arr = list(map(int, sys.stdin.readline().split()))

# dp[i][j] = i번째 인덱스의 수를 가지고 j를 만들 수 있는 경우의 수
dp = [[0] * 21 for _ in range(N)]
dp[0][arr[0]] = 1  # 첫 번째 오는 수는 반드시 포함

# 루프 안에서 다음 숫자를 더하거나/뺏을 때의 결과가 범위 안에 있을 때만 이전 dp값을 더해 줌
for i in range(1, N-1):
    for j in range(21):
        if dp[i-1][j]:
            if (j + arr[i] <= 20):
                dp[i][j + arr[i]] += dp[i-1][j]
            if (j - arr[i] >= 0):
                dp[i][j - arr[i]] += dp[i-1][j]

print(dp[-2][arr[-1]])
