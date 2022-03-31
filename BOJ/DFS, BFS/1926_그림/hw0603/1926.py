from collections import deque
import sys

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

n, m = map(int, sys.stdin.readline().split())
paper = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

def bfs(row, col):
    cnt = 1 # paper 값이 1일때만 bfs()를 호출하므로 최초 좌표는 그림이 맞음

    q = deque()
    q.append([row, col])

    while (q):
        row, col = q.popleft()
        for i in range(4): # 4방향으로 연결된 지점 모두 탐색하며 카운트 증가
            nrow = row + dr[i]
            ncol = col + dc[i]
            if (0 <= nrow < n and 0 <= ncol < m and paper[nrow][ncol] == 1):
                cnt += 1
                paper[nrow][ncol] = 0 # 방문 처리
                q.append([nrow, ncol])
    
    return cnt

painting = [] # 그림들의 크기
for i in range(n):
    for j in range(m):
        if (paper[i][j] == 1):
            paper[i][j] = 0 # 방문 처리
            cnt = bfs(i, j)
            painting.append(cnt) # 탐색 후의 카운트 값을 저장

print(len(painting))
print(f"{max(painting) if painting else 0}")