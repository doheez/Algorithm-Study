from collections import deque
import sys

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]

matrix = [list(sys.stdin.readline().strip()) for _ in range(12)]


# BFS 탐색하면서 연쇄반응 일으킨 후 반응 발생 여부 반환
def bfs(r, c) -> bool:
    puyo_type = matrix[r][c]  # 비교할 뿌요 타입(인자로 들어온 좌표의 뿌요)
    
    q = deque()  # BFS 큐
    q.append((r, c))

    pang = deque()  # 실제로 연쇄반응을 일으키는 뿌요들(BFS큐는 pop하면서 진행해야 하므로 별도로 둠)
    pang.append((r, c))

    visited = [[False] * 6 for _ in range(12)]
    visited[r][c] = True
    flag = False
    count = 1

    while (q):
        row, col = q.popleft()

        for i in range(4):
            n_row = row + dr[i]
            n_col = col + dc[i]

            # 그래프 범위 밖인 경우 continue
            if (n_row < 0 or n_row > 11 or n_col < 0 or n_col > 5):
                continue
            # 방문하지 않았고 새 좌표의 뿌요 타입이 비교할 타입과 같은 경우에만 큐에 추가
            if (not visited[n_row][n_col] and matrix[n_row][n_col] == puyo_type):
                q.append((n_row, n_col))
                pang.append((n_row, n_col))
                visited[n_row][n_col] = True
                count += 1

    # 연속적으로 배치되어 있는 뿌요가 4개 이상일 경우 플래그 설정 후 그 뿌요들을 빈칸으로 만듦
    if (count >= 4):
        flag = True
        for row, col in pang:
            matrix[row][col] = "."

    return flag


# 떠 있는 뿌요들 밑으로 내리는 함수
def organize():
    for col in range(6):
        q = deque()

        # 각 행에 대해 뿌요들의 순서대로 큐에 삽입
        for row in range(11, -1, -1):
            if (matrix[row][col] != '.'):
                q.append(matrix[row][col])
        # 큐에 들어온 순서대로(FIFO) 뿌요들을 아래 칸부터 배치
        for row in range(11, -1, -1):
            matrix[row][col] = q.popleft() if q else '.'


result = 0
while (True):
    istriggered = False

    # 모든 칸을 하나씩 탐색하면서
    for i in range(12):
        for j in range(6):
            # 뿌요가 있는 칸인 경우
            if (matrix[i][j] != '.'):
                # BFS 수행 후 연쇄반응이 일어났으면 플래그 변수에 기록
                if (bfs(i, j)):
                    istriggered = True

    # 전체탐색 1회 진행했는데도 연쇄반응이 한 번도 안 일어난 경우
    if not (istriggered):
        break  # 더 이상 연쇄반응이 일어날 수 없으므로 break
    # 연쇄반응이 일어났다면
    else:
        result += 1  # 카운트 증가
        organize()  # 빈칸 위로 떠 있는 뿌요들 밑으로 다 내림

print(result)
