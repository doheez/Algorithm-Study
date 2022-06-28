import sys

n, m = map(int, sys.stdin.readline().split())
matrix = [list(map(int, list(sys.stdin.readline().strip()))) for _ in range(n)]
dp = [[0] * m for _ in range(n)]

answer = 0
for i in range(m):
    dp[0][i] = matrix[0][i]  # 0행은 자기 자신 말고는 정사각형이 추가될 수 없음
    if (dp[0][i]):
        answer = 1
for i in range(n):
    dp[i][0] = matrix[i][0]  # 0열 역시 마찬가지
    if (dp[i][0]):
        answer = 1


for i in range(1, n):
    for j in range(1, m):
        if (matrix[i][j] == 0):
            dp[i][j] = 0  # matrix[i][j]부터 값이 0인 경우 당연히 정사각형을 이어 나갈 수 없음
        else:
            # DP값을 미리 구해 두었으므로 matrix에서 (i, j)가 추가될 때 i행과 j열의 모든 값이 1인지 검사하지 않고,
            # 단순히 좌측 칸, 윗 칸, 대각선 왼쪽 윗 칸만 보면 됨
            dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
        answer = max(answer, dp[i][j])

print(answer**2)  # answer에는 넓이가 아닌 한 변의 길이를 저장했으므로 제곱하여 출력
# print(*dp, sep='\n')
