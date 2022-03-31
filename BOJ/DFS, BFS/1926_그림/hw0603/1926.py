import sys
sys.setrecursionlimit(10**8)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

n, m = map(int, sys.stdin.readline().split())
paper = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
cnt = 0

def dfs(row, col):
    global cnt
    cnt += 1

    paper[row][col] = 0 # 방문한 곳은 0으로 바꿔줌
    for i in range(4): # 4방향으로 연결된 지점 모두 탐색하며 카운트 증가
        nrow = row + dr[i]
        ncol = col + dc[i]
        if (0 <= nrow < n and 0 <= ncol < m and paper[nrow][ncol] == 1):
            dfs(nrow, ncol)

painting = [] # 그림들의 크기
for i in range(n):
    for j in range(m):
        if (paper[i][j] == 1):
            cnt = 0 # 카운트 초기화
            dfs(i, j)
            painting.append(cnt) # 탐색 후의 카운트 값을 저장

print(len(painting))
print(f"{max(painting) if painting else 0}")