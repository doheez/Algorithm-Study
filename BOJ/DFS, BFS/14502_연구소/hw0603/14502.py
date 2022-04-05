from collections import deque
import sys, copy

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
empty_list = []
virus_list = []
N, M = map(int, sys.stdin.readline().split())
max_cnt = 0


# 연구실 형태 입력받으면서 바이러스와 빈 공간의 위치 저장
lab = [[0]*M for _ in range(N)]
for row in range(N):
    line = [int(col) for col in sys.stdin.readline().split()]
    for col in range(M):
        lab[row][col] = line[col]
        if (lab[row][col] == 0):
            empty_list.append([row, col])
        if (lab[row][col] == 2):
            virus_list.append([row, col])


# 벽 세운 후의 연구실 정보 받아서 BFS 탐색 형태로 바이러스 퍼트린 후 안전 영역의 개수 반환
def spread(lab):
    q = deque()
    visited = [[False]*M for _ in range(N)]

    # 바이러스가 있는 좌표부터 감염을 시작해야 하므로 큐에 삽입
    for virus in virus_list:
        q.append(virus)
    
    while (q):
        row, col = q.popleft()
        for i in range(4):
            nrow = row + dr[i]
            ncol = col + dc[i]
            if (0 <= nrow < N and 0 <= ncol < M): # 새로운 좌표가 배열의 범위를 벗어나지 않고
                if (not visited[nrow][ncol] and lab[nrow][ncol] == 0): # 방문하지 않은 곳이면서 빈 공간이라면
                    visited[nrow][ncol] = True # 방문 표시
                    lab[nrow][ncol] = 2 # 바이러스 배치
                    q.append([nrow, ncol]) # 큐에 해당 좌표 삽입
    
    return sum(row.count(0) for row in lab) # 바이러스 전파 이후 안전 영역의 개수 반환


# 빈 곳들 중 세 곳을 골라 가며 벽 세우기
length = len(empty_list)
for i in range(length):
    for j in range(i+1, length):
        for k in range(j+1, length):
            y1, x1 = empty_list[i]
            y2, x2 = empty_list[j]
            y3, x3 = empty_list[k]

            new_lab = copy.deepcopy(lab) # 원본 배열을 유지해야하므로 deepcopy
            new_lab[y1][x1] = 1
            new_lab[y2][x2] = 1
            new_lab[y3][x3] = 1

            # 세워진 벽에 대해 바이러스 퍼트린 후 안전 영역의 개수 구함
            cnt = spread(new_lab)
            if (cnt > max_cnt):
                max_cnt = cnt

print(max_cnt)
