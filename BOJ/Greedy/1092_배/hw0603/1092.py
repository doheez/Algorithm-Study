import sys

# 데이터 입력받으면서 내림차순 정렬
N = int(sys.stdin.readline())
crane = sorted(list(map(int, sys.stdin.readline().split())), reverse=True)
M = int(sys.stdin.readline())
box = sorted(list(map(int, sys.stdin.readline().split())), reverse=True)


# 박스를 모두 옮길 수 없는 경우 -1 출력
if (box[0] > crane[0]):
    print(-1)
# 박스를 모두 옮길 수 있는 경우
else:
    time = 0
    while (box):
        # 루프 돌면서 박스를 옮길 수 있는 크레인을 찾고, 찾으면 time += 1
        for i in range(N):
            for j in range(len(box)):  # box 리스트의 길이가 계속 변하므로 루프 돌 때 마다 업데이트
                if (crane[i] >= box[j]):  # 이번 크레인으로 박스를 들 수 있으면
                    del box[j]  # box 삭제
                    break
        time += 1
    print(time)
