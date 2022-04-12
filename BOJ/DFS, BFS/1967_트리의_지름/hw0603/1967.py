import sys
sys.setrecursionlimit(10**8)

n = int(sys.stdin.readline())
graph = [[] for _ in range(n+1)]

# 트리 생성: graph[i] = i번째 노드에 연결된 노드들의 (노드 번호, 가중치) 튜플들의 리스트
for _ in range(n-1):
    node1, node2, weight = map(int, sys.stdin.readline().split())
    # 트리는 양방향 연결이므로 두 노드 모두에 연결해 줌
    graph[node1].append((node2, weight))
    graph[node2].append((node1, weight))


def dfs(n, w=0):
    for nodenum, weight in graph[n]:
        if (dist[nodenum] == -1):  # 방문하지 않은 노드에 대하여
            dist[nodenum] = w + weight  # dist 배열에 해당 노드까지의 거리 저장
            dfs(nodenum, dist[nodenum])  # 해당 노드와 연결된 노드에 대해 dfs 탐색 수행


# 임의의 한 노드(루트 노드)에서 가장 멀리 있는 노드를 찾음
dist = [-1] * (n + 1)  # 거리 배열 초기화
dist[1] = 0  # 현재 노드 자신과의 거리는 0
dfs(1)

# 위에서 찾은 노드에서 가장 멀리 있는 노드를 찾음
N2 = dist.index(max(dist))
dist = [-1] * (n + 1)
dist[N2] = 0
dfs(N2)

print(max(dist)) # N2에서 가장 멀리 있는 노드 N3에 대하여, N2와 N3사이의 거리를 출력
