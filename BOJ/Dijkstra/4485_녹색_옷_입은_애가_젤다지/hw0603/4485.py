import heapq
import sys

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]


def dijkstra(graph):
    dist[0][0] = graph[0][0]  # 시작지점의 비용 저장
    pq = [(dist[0][0], (0, 0))]  # pq 초깃값은 시작 지점의 (비용, 좌표)

    while (pq):
        cost, (r, c) = heapq.heappop(pq)

        # pq에서 꺼낸 cost가 현재 구한 최솟값보다 크면 무시
        if (cost > dist[r][c]):
            continue

        # 인접 노드(4방향) 탐색
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if (0 <= nr < N and 0 <= nc < N):  # 새 좌표가 범위 안에 있으면
                new_cost = cost + graph[nr][nc]  # pq에서 꺼낸 좌표를 거쳐서 인접 좌표로 가는 비용
                if (new_cost < dist[nr][nc]):  # 만약 그 비용이 현재까지 구한 비용보다 작다면
                    dist[nr][nc] = new_cost  # 최단거리 업데이트
                    heapq.heappush(pq, (new_cost, (nr, nc)))  # 최단거리가 수정되었으므로 파급효과 조사하기 위해 큐에 push


INF = float('inf')
count = 1
while (N := int(sys.stdin.readline())):
    graph = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    dist = [[INF]*N for _ in range(N)]  # 2차원 그래프 탐색할것이므로 dist 배열도 2차원
    
    dijkstra(graph)
    print(f"Problem {count}: {dist[-1][-1]}")  # dijkstra 탐색 끝난 이후에는 dist[N-1][N-1]에 해당 노드의 최소비용이 저장되어 있음
    count += 1
