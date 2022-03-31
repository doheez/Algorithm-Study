from collections import deque
import sys

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
N, M = map(int, sys.stdin.readline().split())
maze = [list(map(int, list(sys.stdin.readline().strip()))) for _ in range(N)]
cnt = 0

q = deque([(0, 0)])

while (q):
    row, col = q.popleft()
    for i in range(4):  # 4방향으로 연결된 지점 모두 탐색하며 큐에 삽입
        nrow = row + dy[i]
        ncol = col + dx[i]
        if (0 <= nrow < N and 0 <= ncol < M and maze[nrow][ncol] == 1):
            maze[nrow][ncol] = maze[row][col] + 1  # 방문 처리: maze[i][j] => 해당 지점을 방문할 수 있는 최소 이동 횟수
            q.append([nrow, ncol])

print(maze[-1][-1])