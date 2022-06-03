import sys
import heapq

V, E = map(int, sys.stdin.readline().split())
adjList = [[] for _ in range(V+1)]  # 인접리스트
visited = [False]*(V+1)  # 방문 체크

# 인접리스트 구축
for _ in range(E):
    n1, n2, w = map(int, sys.stdin.readline().split())
    adjList[n1].append((w, n2))  # (가중치, 노드번호) -> heapq에서 가중치 순으로 정렬을 위함
    adjList[n2].append((w, n1))

total_cost = 0
cnt = 0
pq = [(0, 1)]  # 최초의 MST 집합은 시작 노드만 포함함
while (pq):
    cost, node = heapq.heappop(pq)  # 현재 MST 집합에서 가장 짧은 간선을 pop()
    if not (visited[node]):  # 그 간선과 연결된 정점이 방문하지 않은 정점이라면
        visited[node] = True  # 방문 처리하고
        total_cost += cost  # 가중치 누적합
        cnt += 1  # 현재까지 선택된 간선의 개수 업데이트

        for item in adjList[node]:  # 그 정점과 인접한 노드들의 정보를 pq에 push()
            heapq.heappush(pq, item)
    
    if (cnt == V):  # 최소 간선(0, 1)을 제외한 간선이 (노드개수-1)개만큼 선택되었다면 MST 구축이 완료된 것이므로 루프 탈출
        break

print(total_cost)
