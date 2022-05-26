import heapq
import sys

INF = float('INF')
V, E = map(int, sys.stdin.readline().split()) # Vertex, Edge
K = int(sys.stdin.readline())  # 시작 노드
graph = [[] for _ in range(V+1)]  # 그래프
visited = [False] * (V+1)
mindist = [INF] * (V+1)  # 시작 노드로부터 각 노드까지의 최단거리

# 그래프 생성
for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))  # (정점, 가중치) tuple


def dijkstra(graph: list, startnode: int):
    mindist[startnode] = 0  # 시작노드->시작노드는 항상 최단거리가 0
    pq = [(mindist[startnode], startnode)]  # 시작 노드 push. (거리, 정점) tuple

    while (pq):  # 큐에 남아있는 노드가 없을 때 까지 반복
        dist, node = heapq.heappop(pq)  # 가중치가 가장 작은 노드 pop

        # 큐에서 pop한 거리가 이미 구해둔 최단거리보다 클 경우(=방문한 셈) 무시함
        if (mindist[node] < dist):
            continue
        # 큐에서 pop한 노드의 인접노드 탐색
        for nextnode in graph[node]:
            cost = mindist[node] + nextnode[1]  # 시작노드->node + node->nextnode 거리
            if (cost < mindist[nextnode[0]]):  # 위에서 구한 cost가 시작노드->nextnode로 바로 가는 거리(현재 구해둔 거리)보다 작으면
                mindist[nextnode[0]] = cost  # 최단거리 업데이트
                heapq.heappush(pq, (cost, nextnode[0]))  # 이번 인접노드를 큐에 추가

dijkstra(graph, K)

for d in mindist[1:]:
    print(f"{'INF' if d == INF else d}")
