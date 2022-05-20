import heapq
import sys

N = int(sys.stdin.readline())
problems = [tuple(map(int, sys.stdin.readline().split())) for _ in range(N)]
problems.sort()  # deadline 빠른 순으로 정렬

pq = []  # 실제로 풀어서 reward를 받을 문제들(을 풀어서 받은 reward)

for p in problems:
    # pq에 reward의 수 push
    # problems가 정렬되어 있으므로 deadline 가까운 순서대로 문제를 취하는 의미
    heapq.heappush(pq, p[1])
    # push 후 pq의 길이가 현재 문제의 deadline 보다 크다면 데드라인을 초과한 것
    # 데드라인을 맞추기 위해 이미 취한 문제를 하나 버려야 하는데, 가장 작은 reward를 pop()을 통해 버림
    if (p[0] < len(pq)):
        heapq.heappop(pq)

print(sum(pq))