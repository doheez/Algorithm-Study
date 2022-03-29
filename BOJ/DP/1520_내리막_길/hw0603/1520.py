import sys
sys.setrecursionlimit(10 ** 8) # 재귀 최대 깊이 제한 확장

# 방향벡터
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

M, N = map(int, sys.stdin.readline().split())

matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
dp = [[-1 for _ in range(N)] for _ in range(M)] # dp[row][col] = (row, col)의 좌표에서 목적지에 도달할 수 있는 경로의 개수. (-1 = 아직 방문하지 않음)

# 시작 좌표를 받아서 그 좌표에서 목적지까지 갈 수 있는 경로의 개수 반환
def explore(row=0, col=0) -> int:
    # 이미 방문한 곳인 경우 저장된 dp 값 반환
    if (dp[row][col] != -1):
        return dp[row][col]

    # 현재 좌표가 이미 목적지인 경우는 한 가지 경우의 수
    if (row == M-1 and col == N-1):
        return 1

    # 방문했던 곳이 아니라면 지금이 첫 방문이므로 dp 값 0으로 초기화 후 dfs를 수행
    dp[row][col] = 0
    # 상하좌우 4방향 탐색
    for i in range(4):
        nrow = row + dr[i]
        ncol = col + dc[i]
        # 새로 만들어진 좌표가 범위 안이고, 이동하는 좌표의 값이 현재 좌표의 값보다 작은 경우에 
        if ((0 <= nrow < M and 0 <= ncol < N) and matrix[nrow][ncol] < matrix[row][col]):
            dp[row][col] += explore(nrow, ncol) # 새로운 dp 값 업데이트
    
    return dp[row][col] # 4방향 모두 탐색 후 나온 현재 좌표의 dp 값 반환 


print(explore())