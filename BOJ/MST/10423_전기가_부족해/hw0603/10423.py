import sys
import heapq

INF = float('INF')
N, M, K = map(int, sys.stdin.readline().split())

graph = [{} for _ in range(N+1)]
plant = list(map(int,sys.stdin.readline().split()))
visited = [False] * (N+1)
dist = [INF for _ in range(N+1)]

# 인접리스트 구축
for _ in range(M):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u][v] = min(graph[u].get(v, INF), w)
    graph[v][u] = min(graph[v].get(u, INF), w)


pq = []
result = 0
# 최초 HeapQ에 존재하는 발전소들의 간선을 모두 미리 넣어줌 -> 발전기 설치 지점이 트리의 루트가 되는 효과
for start in plant:
    heapq.heappush(pq, (0, start))
    dist[start] = 0

# MST 구함
while (pq):
    dis, node = heapq.heappop(pq)
    if (visited[node]):
        continue
    visited[node] = True
    result += dis

    for next_node in graph[node]:
        if (dist[next_node] > graph[node][next_node]):
            dist[next_node] = graph[node][next_node]
            heapq.heappush(pq, (dist[next_node], next_node))

print(result)
