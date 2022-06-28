import sys

N, K = map(int, sys.stdin.readline().split())
dp = [[0] * (N+1)] + [[1] * (N+1) for _ in range(K)]

for i in range(1, K+1):
    for j in range(1, N+1):
        dp[i][j] = dp[i-1][j] + dp[i][j-1]

print(dp[-1][-1] % 1000000000)
