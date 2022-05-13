from bisect import bisect_left, bisect_right
import sys

N = int(sys.stdin.readline())
crane = sorted(list(map(int, sys.stdin.readline().split())))
M = int(sys.stdin.readline())
box = sorted(list(map(int, sys.stdin.readline().split())))

min_crane, max_crane = crane[0], crane[-1]
min_box, max_box = box[0], box[-1]

crane = crane[bisect_left(crane, min_box):]  # 박스의 최소 무게보다 허용중량이 큰 크레인만 남김
cranecpy = crane[:]

# 박스를 모두 옮길 수 없는 경우 -1 출력
if (not crane or max_box > max_crane):
    print(-1)
# 박스를 모두 옮길 수 있는 경우
else:
    limitidx = {c: bisect_right(box, c)-1 for c in crane}
    days = 1
    print(f"{limitidx=}")
    print(f"{box=}")
    print(f"{crane=}")

    for i in range(len(box)):
        if not (crane):
            crane = cranecpy[:]
            days += 1
        while (crane):
            minavailcrane = crane.pop(0)
            if (i <= limitidx[minavailcrane]):
                break
        print(f"박스 {box[i]} 에는 크레인 {minavailcrane} 할당. 남은 크레인은 {crane}")

    print(days)
