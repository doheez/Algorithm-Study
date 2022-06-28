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
        if (matrix[i][j] == 1):

            target_cnt = int(dp[i-1][j-1]**(1/2))  # 정사각형을 이어 나가기 위해 좌, 상으로 조사해야 할 타일 개수
            will_be_add = 1  # 현재 보고 있는 matrix[i][j]는 이미 1이므로 조건이 만족하면 넓이에 추가될 것임

            for k in range(1, target_cnt+1):
                if (matrix[i][j-k] == 1 and matrix[i-k][j] == 1):  # 1칸씩 좌/상으로 이동하며 그 칸의 값이 1인지 확인
                    will_be_add += 2
                else:  # 하나라도 1이 아니라면 정사각형을 이어 나갈 수 없는 것이므로 0으로 초기화
                    will_be_add = 0
                    dp[i][j] = matrix[i][j]
                    break
            else:
                dp[i][j] = dp[i-1][j-1] + will_be_add  # 정사각형을 이어나갈 수 있는 만큼의 추가된 넓이를 더해줌

            if (answer < dp[i][j]):
                answer = dp[i][j]  # 넓이 최댓값 업데이트
        
        else:  # matrix[i][j]부터 값이 0인 경우 당연히 정사각형을 이어 나갈 수 없음
            dp[i][j] = 0

print(answer)
print(*dp, sep='\n')
