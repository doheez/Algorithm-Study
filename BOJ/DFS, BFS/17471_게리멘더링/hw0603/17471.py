from itertools import combinations
from collections import deque
import sys

N = int(sys.stdin.readline().strip())
population = list(map(int, sys.stdin.readline().rstrip().split()))
graph = [[0 for _ in range(N)] for _ in range(N)]

for i in range(N):
    _, *dsts = map(int, sys.stdin.readline().split())
    for dst in dsts:
        graph[i][dst-1] = 1


def isConnected(nodes):
    q = deque()
    q.append(nodes[0])
    check = [False for _ in range(N)]
    check[nodes[0]] = True

    while (q):
        node = q.popleft()

        for i in range(len(graph[node])):
            if (graph[node][i] == 0):
                continue
            if (i not in nodes):
                continue
            if not (check[i]):
                check[i] = True
                q.append(i)

    return (check.count(True) == len(nodes))


def get_total(nodes):
    total = 0
    for node in nodes:
        total += population[node]

    return total


cases = []
X = {i for i in range(N)}
INF = sys.maxsize
ans = INF

for i in range(1, N//2+1):
    As = tuple(combinations(X, i))
    for A in As:
        B = list(X.difference(A))

        if (isConnected(A) and isConnected(B)):
            a_total = get_total(A)
            b_total = get_total(B)
            ans = min(ans, abs(a_total-b_total))

print(-1 if ans == INF else ans)
