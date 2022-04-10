import sys

N = int(sys.stdin.readline())
arr = [[] for _ in range(N+1)]
visited = [False for _ in range(N+1)]
result = []

# 문제 데이터에서 1행의 수가 0행을 가리키고 있다고 생각하고 graph 형성
for i in range(1, N+1):
    arr[int(sys.stdin.readline())].append(i)

# 노드 번호 받아서 dfs 탐색 후 사이클을 발견하면 정답 셋에 추가
def dfs(nodenum: int, pathset: set):
    visited[nodenum] = True # 방문 처리
    pathset.add(nodenum)
    
    for nextnode in arr[nodenum]:
        if nextnode not in pathset:
            dfs(nextnode, pathset.copy()) # dfs 깊이 별로 경로를 별도로 유지해야 하므로 복사본을 전달해 줌
        else: # 지금까지의 경로에 존재하는 노드가 또 들어오는 경우 사이클이 생긴 것
            result.extend(list(pathset)) # 사이클이 생기면 그 사이클을 구성하는 경로를 result에 저장

# 각 노드에 대해서 dfs 호출
for i in range(1, N+1):
    if not (visited[i]): # 방문하지 않은 노드에 대해서만 dfs() 호출
        dfs(i, set()) # 각 dfs 단계에서의 경로를 저장하기 위한 빈 집합을 두 번째 인자로 전달

print(len(result))
print(*sorted(set(result)), sep="\n") # 중복 제거 후 정렬하여 출력