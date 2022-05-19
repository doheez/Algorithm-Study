import sys

N = int(sys.stdin.readline())
chu_list = sorted(list(map(int, sys.stdin.readline().strip().split())))

curmin = 0  # 현재 추들로 측정할 수 있는 무게의 최솟값
curmax = 0  # 현재 추들로 측정할 수 있는 무게의 최댓값
chu_sum = 0  # 현재 추들의 무게합

# 1부터 측정이 불가능한 경우
if (chu_list[0] != 1):
    print(1)
    sys.exit()

for chu in chu_list:
    chu_sum += chu

    # 새롭게 추가되는 추로 인해 생기는 측정 가능한 무게의 범위
    newmin = chu
    newmax = curmax + chu

    # print(f"추가된 추: {chu}; 추가되기 전 범위: {curmin}~{curmax}; 추가된 범위: {newmin}~{newmax}; 추가 이후 합: {chu_sum}")

    # 새롭게 추가되는 범위가 curmin <= newmin <= curmax+1 <= newmax == chu_sum 인 경우
    # 이 경우에만 현재까지 추가된 추들의 최대 측정치 이하 무게들이 모두 측정 가능함
    if ((newmin <= curmax+1) and (newmax == chu_sum)): # 사실 newmax == chu_sum은 항상 만족할 것.
        curmin = 1  # 측정 가능한 최솟값은 항상 1
        curmax = newmax
    else:
        print(curmax+1)
        break
else:
    print(chu_sum+1)
