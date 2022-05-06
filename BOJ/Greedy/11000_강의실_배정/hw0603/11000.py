import sys
import heapq

N = int(sys.stdin.readline())
timetable = sorted([tuple(map(int, sys.stdin.readline().split())) for _ in range(N)])  # 빨리 시작하는 강의부터 정렬
classroom = []

heapq.heappush(classroom, timetable[0][1])  # 힙큐에 첫 강의의 종료시간 push

for i in range(1, N):
    if (timetable[i][0] < classroom[0]):  # i번째 강의의 시작 시간이 현재 강의실 중 가장 빠른 강의 종료시간보다 이르면
        heapq.heappush(classroom, timetable[i][1])  # 새로운 강의실 사용
    else:  # 현재 강의실을 그대로 사용 가능할 때
        heapq.heapreplace(classroom, timetable[i][1])  # pop -> push (가장 종료시간이 빠른 강의 pop 후 현재 강의정보로 업데이트)

print(len(classroom))
