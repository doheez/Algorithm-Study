import sys
from collections import deque
# 1: 시계방향, -1: 반시계 방향

def check_right(start, dirs):
    # 더 이상 조사가 불가능한 경우
    if (start > 4 or gears[start-1][2] == gears[start][6]):
        return

    # 오른쪽 확인
    if (gears[start-1][2] != gears[start][6]):
        # 인접한 톱니바퀴가 회전 가능한지부터 먼저 파악한다.
        check_right(start + 1, -dirs)
        # 회전시킨다.
        gears[start].rotate(dirs)


def check_left(start, dirs):
    if (start < 1 or gears[start][2] == gears[start+1][6]):
        return

    # 왼쪽 확인
    if (gears[start+1][6] != gears[start][2]):
        check_left(start - 1, -dirs)
        gears[start].rotate(dirs)


gears = {}
for i in range(1, 5):
    gears[i] = deque(list(map(int, list(sys.stdin.readline().replace("\n", "")))))
K = int(sys.stdin.readline())

for _ in range(K):
    num, dirs = map(int, sys.stdin.readline().split())

    # 기준 톱니바퀴 좌우를 확인하고, 회전이 가능한 경우 회전
    check_right(num+1, -dirs)
    check_left(num-1, -dirs)

    # 기준 톱니바퀴를 회전
    gears[num].rotate(dirs)


# 점수 계산
score = 0
for i in range(1, 5):
    if (s := gears[i][0]):
        score += (s * (2**(i-1)))

print(score)
