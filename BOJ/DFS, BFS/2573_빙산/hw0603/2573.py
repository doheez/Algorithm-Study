from collections import deque
import sys

dr = [0, 0, 1, -1]
dc = [1, -1, 0, 0]

N, M = map(int, sys.stdin.readline().split())
matrix = [[0] * M for _ in range(N)]
ice_list = []
melting_q = deque()


def bfs(row, col):
    total_checked_ice = 1
    q = deque([(row, col)])
    while (q):
        row, col = q.popleft()
        visited[row][col] = True
        for i in range(4):
            n_row = row + dr[i]
            n_col = col + dc[i]
            
            if (0 <= n_row < N and 0 <= n_col < M):  # 범위에 맞고
                if (matrix[n_row][n_col] and not visited[n_row][n_col]):  # 얼음이 있으며 아직 방문하지 않은 곳이라면
                    visited[n_row][n_col] = True
                    total_checked_ice += 1
                    q.append((n_row, n_col))  # 큐에 추가
                elif (matrix[n_row][n_col] == 0):  # 바닷물이라면
                    melting_q.append((row, col))  # 얼음 하나 녹이기
    
    return total_checked_ice




# 빙산 위치를 입력받으면서 빙산이 있는 곳을 리스트에 따로 저장
for i in range(N):
    line = list(map(int, sys.stdin.readline().split()))
    for j, item in enumerate(line):
        matrix[i][j] = item
        if (item):
            ice_list.append((i, j))


remain_ice = len(ice_list)
year = 0
while (ice_list):
    visited = [[False] * M for _ in range(N)]
    # BFS 탐색을 통해 녹일 빙산들을 구함
    found_ice = bfs(*ice_list[0])
    # 빙산이 두 조각으로 갈라진 경우 break
    if (found_ice != remain_ice):
        print(year)
        sys.exit()
    ice_list.clear()
    # 실제로 빙산을 녹임
    while (melting_q):
        r, c = melting_q.popleft()
        matrix[r][c] = max(matrix[r][c] - 1, 0)
    # 다 녹이고 나서 얼음이 남아있는 경우 리스트에 추가
    for i in range(N):
        for j in range(M):
            if (matrix[i][j]):
                ice_list.append((i, j))

    remain_ice = len(ice_list)
    # print(ice_list)
    # print(matrix)
    year += 1

print(0)
