import sys
sys.setrecursionlimit(10**8)

dr = [0, 0, 1, -1]
dc = [1, -1, 0, 0]

N = int(sys.stdin.readline())
matrix = [list(sys.stdin.readline().strip()) for _ in range(N)]
visited = [[False]*N for _ in range(N)]
count = {"R": 0, "G": 0, "B": 0}


def dfs(row, col, color):
    visited[row][col] = True

    for i in range(4):
        n_row = row + dr[i]
        n_col = col + dc[i]

        if (0 <= n_row < N and 0 <= n_col < N):
            if not (visited[n_row][n_col]):
                if (matrix[n_row][n_col] == color):
                    dfs(n_row, n_col, color)

# 색약이 아닐 때
for i in range(N):
    for j in range(N):
        if not (visited[i][j]):
            dfs(i, j, matrix[i][j])
            count[matrix[i][j]] += 1
cnt1 = sum(count.values())

# 색약일 때
visited = [[False]*N for _ in range(N)]
count = {"R": 0, "G": 0, "B": 0}
for i in range(N):
    for j in range(N):
        if (matrix[i][j] == "R"):
            matrix[i][j] = "G"
for i in range(N):
    for j in range(N):
        if not (visited[i][j]):
            dfs(i, j, matrix[i][j])
            count[matrix[i][j]] += 1
print(cnt1, sum(count.values()))
