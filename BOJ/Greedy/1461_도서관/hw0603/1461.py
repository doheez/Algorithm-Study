import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
books = list(map(int, sys.stdin.readline().rstrip().split()))
minus_axis, plus_axis = list(), list()

# books 리스트에서 +축과 -축 분리하고 거리가 먼 순서대로 정렬
for book in books:
    (plus_axis if book > 0 else minus_axis).append(abs(book))
minus_axis.sort(reverse=True)
plus_axis.sort(reverse=True)

# 거리가 가장 먼 위치
max_dist = abs(max(books, key=lambda x: abs(x)))

# 방문한 후 남은 책을 다시 가지러 가야 하는 좌표의 거리
walk_cnt = minus_axis[::M] + plus_axis[::M]

# 가장 마지막 위치(가장 먼 곳)에서는 원점으로 돌아올 필요 없으므로 왕복 후 그 거리를 빼 줌
print(2*sum(walk_cnt) - max_dist)
