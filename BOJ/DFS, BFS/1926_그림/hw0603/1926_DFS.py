import sys
sys.setrecursionlimit(10**8)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

n, m = map(int, sys.stdin.readline().split())
paper = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]


# def dfs(row, col):
#     cnt[0] += 1

#     paper[row][col] = 0  # 방문한 곳은 0으로 바꿔줌
#     for i in range(4):  # 4방향으로 연결된 지점 모두 탐색하며 카운트 증가
#         nrow = row + dr[i]
#         ncol = col + dc[i]
#         if (0 <= nrow < n and 0 <= ncol < m and paper[nrow][ncol] == 1):
#             dfs(nrow, ncol)


def dfs(row, col):
    if (row < 0 or row >= n or col < 0 or col >= m):
        return
    if (paper[row][col] == 0):
        return
    cnt[0] += 1

    paper[row][col] = 0 # 방문한 곳은 0으로 바꿔줌

    # 아니 이건 안되는데
    # for i in range(4):  # 4방향으로 연결된 지점 모두 탐색하며 카운트 증가
    #     dfs(row + dr[i], col + dc[i])

    # 이것도 안되는데ㅋㅋ
    # for rowcol in ((row+1, col), (row-1, col), (row, col+1), (row, col-1)):
    #     dfs(*rowcol)

    # 당연히 이것도 안되겠지? ㅇㅇ;
    # for r, c in zip((row+1, row-1, row, row), (col, col, col+1, col-1)):
    #     dfs(r, c)

    # 이건 왜 됨?
    dfs(row+1, col)
    dfs(row-1, col)
    dfs(row, col+1)
    dfs(row, col-1)

    # 반복문 쓰면 터지는 줄 알았더니..
    # https://www.acmicpc.net/source/27878917 
    # 이사람은 for문 써서 잘 풀었네? 무엇;


max_painting = 0  # 그림들의 크기
total_painting = 0 # 그림들의 개수
for i in range(n):
    for j in range(m):
        if (paper[i][j] == 1):
            cnt = [0]  # 카운트 초기화
            dfs(i, j)
            total_painting += 1
            max_painting = max(max_painting, cnt[0])  # 탐색 후의 카운트 값을 저장

print(total_painting)
print(f"{max_painting if total_painting else 0}")
