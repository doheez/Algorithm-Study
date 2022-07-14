from collections import deque
import sys

K = int(sys.stdin.readline())

for _ in range(K):
    V, E = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(V+1)]
    for _ in range(E):
        v1, v2 = map(int, sys.stdin.readline().split())
        graph[v1].append(v2)
        graph[v2].append(v1)
    
    
    result = "YES"
    startcandidate = [True] * (V+1)  # 시작 노드 후보군
    for n in range(1, V+1):  # 1번 정점부터 V번 정점을 시작 노드로 두고 탐색
        if not (startcandidate[n]):  # 이번 노드가 시작 노드 후보군이 아닐 경우 continue
            continue
        nodeType = [None] * (V+1)
        nodeType[n] = 1  # 시작 노드의 타입을 1로 설정
        q = deque([n])  # 시작 노드 큐에 넣기

        while (q):
            node_num = q.popleft()
            node_type = nodeType[node_num]
            for adjnode in graph[node_num]:  # 인접한 노드 중
                if not (nodeType[adjnode]):  # 방문하지 않은 노드에 대하여
                    nodeType[adjnode] = node_type * (-1)  # 현재노드와 다른 값으로 타입 변경
                    q.append(adjnode)  # 큐에 추가하여 다음번에 방문할 수 있도록
                    startcandidate[adjnode] = False  # 한 번 방문한 노드는 시작 노드 후보군에서 제외
                else:  # 이미 방문한 노드 중
                    if (nodeType[adjnode] == node_type):  # 인접 정점이 현재노드와 같은 타입이라면
                        q.clear()  # 큐를 clear() 하여 while 루프 탈출
                        result = "NO"
                        break  # 이분그래프가 될 수 없음
        
        if (result == "NO"):
            break
    print(result)
