from collections import deque
import sys

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

N, L, R = map(int, sys.stdin.readline().split())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]


def bfs(i, j):
    q = deque([(i, j)])
    visited[i][j] = True

    union = [(i, j)]  # 연합을 이루는 국가
    count = matrix[i][j]  # 연합의 총 인구 수
    
    while (q):
        x, y = q.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if (nx < 0 or ny < 0 or nx >= N or ny >= N):  # 그래프 범위 밖인 경우 continue
                continue
            if (visited[nx][ny]):  # 이미 방문한 지점 제외
                continue
            if (L <= abs(matrix[nx][ny] - matrix[x][y]) <= R):  # 인구 차이가 L~R명인 경우 연합에 추가
                visited[nx][ny] = True
                count += matrix[nx][ny]
                q.append((nx, ny))
                union.append((nx, ny))

    # 연합 내 국가의 인구 = (연합의 총 인구) / (연합의 크기)
    for x, y in union:
        matrix[x][y] = int(count / len(union))  # 소수점 버림

    return len(union)

day = 0
while (True):
    visited = [[False] * N for _ in range(N)]
    ismove = False  # 인구 이동 플래그
    # BFS 탐색하며 연합 생성
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                ismove = True if (bfs(i, j) > 1) else ismove
    if not (ismove):  # 인구 이동이 없는 경우 루프 탈출
        break
    day += 1

print(day)
