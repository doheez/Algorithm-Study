from itertools import combinations
from collections import deque
import sys

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

N, M = map(int, sys.stdin.readline().split())
lab = [list(map(int, input().rstrip('\n').split())) for _ in range(N)]

virus_pos = []  # 바이러스 위치
empty_cnt = 0  # 빈 칸의 개수
INF = sys.maxsize  # 무한대


def bfs(q: deque, empty_cnt: int) -> int:
    visited = [[-1] * N for _ in range(N)]

    time = 0
    while (True):
        length = len(q) # 큐의 길이(=1초 동안 새롭게 추가된 바이러스의 수)
        if not (q):  # 바이러스를 전체에 퍼트리는 것이 불가능할 경우
            return INF
        if (empty_cnt == 0):  # 더 이상 빈 칸이 없을 경우(모든 칸이 감염된 경우) time 반환
            return time

        time += 1
        for _ in range(length):  # 큐 길이만큼만 반복! 중요!! 부모 노드에서 파생된 애들만 보기 위함
            x, y = q.popleft()
            if (visited[x][y] == -1):
                visited[x][y] = 1

            for d in range(4):
                nx = x + dx[d]
                ny = y + dy[d]

                if (0 <= nx < N and 0 <= ny < N):  # 범위 안에 있고
                    if (visited[nx][ny] == -1):  # 아직 방문하지 않은 칸이면서
                        if (lab[nx][ny] == 0):  # 빈 칸인 경우
                            q.append((nx, ny))
                            visited[nx][ny] = 1
                            empty_cnt -= 1
                        elif (lab[nx][ny] == 2):  # 비활성화된 바이러스 칸인 경우
                            q.append((nx, ny))
                            visited[nx][ny] = 1


# 완전탐색으로 빈 칸의 개수를 구한 후, 바이러스들의 위치 정보 저장
for i in range(N):
    for j in range(N):
        if not (lab[i][j]):
            empty_cnt += 1  # 빈 칸의 개수 업데이트
        elif (lab[i][j] == 2):
            virus_pos.append((i, j))  # 바이러스 위치 정보 저장


# 바이러스를 활성 상태로 만들 수 있는 모든 조합에 대해 체크
result = INF
for virus_list in combinations(virus_pos, M):  # (v_p)_C_(M)
    q = deque(virus_list)
    retval = bfs(q, empty_cnt)  # BFS 수행
    result = min(result, retval)  # 최솟값 업데이트

print(-1 if result == INF else result)
