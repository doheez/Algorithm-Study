from collections import deque
import sys

N, M = map(int,sys.stdin.readline().split())
matrix = []
visited = [[False] * M for _ in range(N)]
r, c, d = map(int,sys.stdin.readline().split())

# d: 0 -> 3 -> 2 -> 1 순서대로 돌 수 있도록 설정
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for _ in range(N):
    matrix.append(list(map(int,sys.stdin.readline().split())))

visited[r][c] = True
cnt = 1

while (True):
    isCleaned = False
    # 4방향 확인
    for _ in range(4):
        # 0 -> 3 -> 2 -> 1 순서로
        nx = r + dx[(d+3) % 4]
        ny = c + dy[(d+3) % 4]

        # 한번 돌았으면 그 방향으로 작업시작
        d = (d+3) % 4
        if (0 <= nx < N and 0 <= ny < M and matrix[nx][ny] == 0):
            if not (visited[nx][ny]):
                visited[nx][ny] = True
                cnt += 1
                r, c = nx, ny
                
                # 한 방향이라도 청소를 했다면 다음으로 넘어감
                isCleaned = True
                break
    
    # 4방향 모두 청소가 되어 있거나 벽인 경우
    if (isCleaned == False):
        if (matrix[r-dx[d]][c-dy[d]] == 1):  # 4. 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우
            print(cnt)
            break  # 작동 멈춤
        else:  # 3. 바라보는 뱡항을 유지한 채로 한 칸 후진 후 [2] 로 돌아감
            r, c = r-dx[d], c-dy[d]