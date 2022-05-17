import heapq
import sys

N = int(sys.stdin.readline())
cardlist = [int(sys.stdin.readline()) for _ in range(N)]
heapq.heapify(cardlist)

result = 0
while (len(cardlist) > 1):
    compare = heapq.heappop(cardlist)+heapq.heappop(cardlist)  # 현재 덱들 중 가장 작은 두 덱 비교
    result += compare
    heapq.heappush(cardlist, compare)  # 비교 후 생성된 덱 큐에 추가

print(result)
