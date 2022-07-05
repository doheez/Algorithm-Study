import sys

N = int(sys.stdin.readline())

matrix = [[0] * N] + [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [[[0, 0, 0] for _ in range(N)] for _ in range(N+1)]  # 가로, 대각선, 세로

dp[1][1] = [1, 0, 0]  # DP 초깃값


for i in range(1, N+1):
    for j in range(1, N):
        if (i == j == 1):
            continue
        if (matrix[i][j] == 0):
            dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1]  # 가로
            dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2]  # 세로
            if (matrix[i][j-1] == matrix[i-1][j] == 0):
                dp[i][j][1] = sum(dp[i-1][j-1])  # 대각선


print(sum(dp[-1][-1]))
