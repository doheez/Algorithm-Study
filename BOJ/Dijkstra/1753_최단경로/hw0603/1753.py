import heapq
import sys

INF = float('INF')
V, E = map(int, sys.stdin.readline().split()) # Vertex, Edge
K = int(sys.stdin.readline())  # 시작 노드
graph = [[] for _ in range(V+1)]  # 그래프
mindist = [INF] * (V+1)  # 시작 노드로부터 각 노드까지의 최단거리

# 그래프 생성
for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))  # (정점, 가중치) tuple


def dijkstra(graph: list, startnode: int) -> None:
    mindist[startnode] = 0  # 시작노드->시작노드는 항상 최단거리가 0
    pq = [(mindist[startnode], startnode)]  # 시작 노드 push. (거리, 정점) tuple

    while (pq):  # 큐에 남아있는 노드가 없을 때 까지 반복
        dist, node = heapq.heappop(pq)  # 현재 방문한 노드집합의 인접노드 중 가중치가 가장 작은 노드 pop

        # 큐에서 pop한 거리가 이미 구해둔 최단거리보다 클 경우(=방문한 셈) 무시함
        # pq를 사용했기 때문에 별도의 visited 배열이 필요 없다. pq에서 pop()한 노드를 탐색하려 하는데,
        # 해당 노드보다 이미 mindist[] 에 구해둔 거리값이 더 짧으면 그 노드는 조사할 필요가 없음 -> 무시
        if (mindist[node] < dist):
            continue
        # 큐에서 pop한 노드의 인접노드 탐색
        for nextnode in graph[node]:
            cost = mindist[node] + nextnode[1]  # 시작노드->node + node->nextnode 거리
            if (cost < mindist[nextnode[0]]):  # 위에서 구한 cost가 시작노드->nextnode로 바로 가는 거리(현재 구해둔 거리)보다 작으면
                mindist[nextnode[0]] = cost  # 최단거리 업데이트
                heapq.heappush(pq, (cost, nextnode[0]))  # 이번 인접노드를 큐에 추가. 최단거리가 업데이트되었으므로 그에 따른 파급효과(이 노드를 거쳐 가면 더 짧게 도달할 수 있는 노드가 있는가?)를 다시 계산하기 위함.

dijkstra(graph, K)

for d in mindist[1:]:
    print(f"{'INF' if d == INF else d}")
