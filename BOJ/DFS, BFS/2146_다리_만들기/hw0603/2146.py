from collections import deque
import sys

sys.setrecursionlimit(10 ** 8)
dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]

N = int(sys.stdin.readline())
graph = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
visited = [[False] * N for _ in range(N)]


# # BFS로 각 섬을 다른 번호로 마킹하는 함수
# def markisland(i, j):
#     q = deque([(i, j)])
#     visited[i][j] = True

#     while (q):
#         row, col = q.popleft()

#         graph[row][col] = markisland.marknum

#         for d in range(4):
#             n_row = row + dr[d]
#             n_col = col + dc[d]

#             if (0 <= n_row < N and 0 <= n_col < N):  # 그래프 범위 안에 있고
#                 if (graph[n_row][n_col] == 1):  # 아직 마킹되지 않은 경우
#                     q.append((n_row, n_col))
#                     visited[n_row][n_col] = True

#     markisland.marknum += 1

# 각 섬에 번호를 붙여줘서 그룹핑하는 함수
def markisland(row, col):
    visited[row][col] = True
    graph[row][col] = markisland.marknum

    for i in range(4):
        n_row, n_col = row + dr[i], col + dc[i]
        if (n_row < 0 or n_row >= N or n_col < 0 or n_col >= N):
            continue
        if (not visited[n_row][n_col] and graph[n_row][n_col]):
            markisland(n_row, n_col)


# 바다를 건너며 섬간 최단거리 구함
# 그래프에서 z값에 해당하는 곳: 현재 호출된 함수에서 조사할 섬(탐색시작 위치)
def bfs(z):
    global answer
    dist = [[-1] * N for _ in range(N)]  # 거리가 저장될 배열
    
    q = deque()

    # z값과 일치하는 섬의 모든 좌표를 큐에 삽입
    for i in range(N):
        for j in range(N):
            if (graph[i][j] == z):
                q.append([i, j])
                dist[i][j] = 0  # 각 좌표는 시작지점이므로 거리가 0

    while (q):
        row, col = q.popleft()
        
        for i in range(4):
            n_row = row + dr[i]
            n_col = col + dc[i]
            
            # 갈 수 없는 곳이면 continue
            if (n_row < 0 or n_row >= N or n_col < 0 or n_col >= N):
                continue
            # 다른 섬을 만나면 기존 답과 비교하여 짧은 거리 선택
            if (graph[n_row][n_col] > 0 and graph[n_row][n_col] != z):
                answer = min(answer, dist[row][col])
                return
            # 바다를 만나면 dist값 1씩 증가
            if (graph[n_row][n_col] == 0 and dist[n_row][n_col] == -1):
                dist[n_row][n_col] = dist[row][col] + 1
                q.append([n_row, n_col])


# 각 섬에 독립적인 번호를 부여
markisland.marknum = 2  # 1은 이미 육지 칸의 초깃값이므로 2부터 마킹
for i in range(N):
    for j in range(N):
        if (graph[i][j] == 1 and not visited[i][j]):  # 아직 마킹되지 않은 경우
            markisland(i, j)  # 해당 좌표를 포함하는 섬을 마킹
            markisland.marknum += 1

# 각 좌표를 BFS탐색하며 나와 다른 섬으로 이동하기까지 건너야 하는 바다 칸의 개수(=다리의 길이) 구함
answer = sys.maxsize
for i in range(2, markisland.marknum):
    bfs(i)


# print(*graph, sep="\n")
print(answer)