import sys

N = int(sys.stdin.readline())
dp = [[(0, 0)] * 3 for _ in range(2)]
matrix = [list(map(int, sys.stdin.readline().split()))]

# DP 배열 초깃값 지정 (max, min)
for j in range(3):
    dp[0][j] = (matrix[0][j], matrix[0][j])
# DP 배열 값 계산
for _ in range(N-1):
    matrix = [list(map(int, sys.stdin.readline().split()))]
    dp[1][0] = (matrix[0][0] + max((dp[0][0][0], dp[0][1][0])), matrix[0][0] + min((dp[0][0][1], dp[0][1][1])))
    dp[1][1] = (matrix[0][1] + max((dp[0][0][0], dp[0][1][0], dp[0][2][0])), matrix[0][1] + min((dp[0][0][1], dp[0][1][1], dp[0][2][1])))
    dp[1][2] = (matrix[0][2] + max((dp[0][1][0], dp[0][2][0])), matrix[0][2] + min((dp[0][1][1], dp[0][2][1])))
    
    dp[0], dp[1] = dp[1], dp[0]  # DP 배열 swap

print(max(dp[0])[0], min(dp[0], key=lambda x: x[1])[1])
