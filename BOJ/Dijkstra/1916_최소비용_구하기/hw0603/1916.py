import heapq
import sys

INF = float('inf')
N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
graph = [[] for _ in range(N+1)]
dist = [INF] * (N+1)

# 그래프 구축
for _ in range(M):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))

start, dest = map(int, sys.stdin.readline().split())

def dijkstra(graph: list, start: int) -> None:
    dist[start] = 0
    pq = [(dist[start], start)]

    while (pq):
        cost, node = heapq.heappop(pq)

        if (cost > dist[node]):
            continue
        for adjnode in graph[node]:
            new_cost = dist[node] + adjnode[1]  # 시작노드->node + node->adjnode 거리합
            # new_cost = cost + adjnode[1] vs new_cost = dist[node] + adjnode[1] 어느게 맞나?
            if (new_cost < dist[adjnode[0]]):
                dist[adjnode[0]] = new_cost
                heapq.heappush(pq, (new_cost, adjnode[0]))

dijkstra(graph, start)
print(dist[dest])