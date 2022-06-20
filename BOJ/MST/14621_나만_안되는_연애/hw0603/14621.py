import sys
import heapq

N, M = map(int, sys.stdin.readline().split())
univ_type = [0] + sys.stdin.readline().split()
visited = [False]*(N+1)  # 방문 체크
adjList = [[] for _ in range(N+1)]  # 인접리스트

# 인접리스트 구축
for _ in range(M):
    n1, n2, w = map(int, sys.stdin.readline().split())
    adjList[n1].append((w, n2))  # (가중치, 노드번호) -> heapq에서 가중치 순으로 정렬을 위함
    adjList[n2].append((w, n1))

total_cost = 0
cnt = 0
pq = [(0, 1, None, univ_type[1])]  # 최초 MST 집합. (비용, 노드, 인접한 노드의 타입, 타입)

while (pq):
    cost, node, adj_node_type, type = heapq.heappop(pq)  # 현재 MST 집합에서 가장 짧은 간선을 pop()
    if (not visited[node] and adj_node_type != type):  # 그 간선과 연결된 정점이 방문하지 않은 정점이라면
        visited[node] = True  # 방문 처리하고
        total_cost += cost  # 가중치 누적합
        cnt += 1  # 현재까지 선택된 간선의 개수 업데이트

        for adjcost, adjnode in adjList[node]:  # 그 정점과 인접한 노드들의 정보를 pq에 push()
            if not (visited[adjnode]): # 인접노드 방문 체크
                if (univ_type[adjnode] != type): # 인접노드의 타입이 현재 노드와 다르다면
                    heapq.heappush(pq, (adjcost, adjnode, univ_type[node], univ_type[adjnode])) # MST 집합에 추가

    if (cnt == N):  # 최소 간선을 제외한 간선이 (노드개수-1)개만큼 선택되었다면 MST 구축이 완료된 것이므로 루프 탈출
        print(total_cost)
        sys.exit(0)

print(-1)
