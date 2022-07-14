from collections import deque
import sys

F, S, G, U, D = map(int, sys.stdin.readline().split())

"""
자.. 생각을 해 보면
큐에다가 cnt랑 현재 노드의 번호를 넣어서 상태를 유지하고, 
bfs 탐색하다가 현재 층이 G랑 동일해 지면, 그 때의 cnt가 정답
큐가 비어서 루프 탈출하면 엘베로 못 이동하는 것
"""

isFound = False
q = deque([(S, 0)])  # (현재 층, 버튼 누른 횟수)
visited = {}

while (q):
    cur, cnt = q.popleft()

    if (cur == G):
        print(cnt)
        isFound = True
        break
    
    if (not visited.get(cur+U) and cur + U <= F):
        q.append((cur + U, cnt+1))
        visited[cur+U] = True
    if (not visited.get(cur-D) and cur - D >= 1):
        q.append((cur - D, cnt+1))
        visited[cur-D] = True

if not (isFound):
    print("use the stairs")
