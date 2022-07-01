from collections import deque

S = int(input())
dp = {}  # DP 딕셔너리; 그 값은 해당 상태를 만들기 위해 소요되는 시간
dp[(1, 0)] = 0
q = deque([(1, 0)])  # (출력한 이모티콘 수, 클립보드에 저장된 이모티콘 수) -> 처음에 하나를 입력한 상태로 시작하므로

while (q):
    wri, clip = q.popleft()
    
    # clip에 관계없이 wri 값이 S와 같으면 출력 완료된 것
    if (wri == S):
        break
        
    # 1) 전체선택 -> 복사 후 클립보드에 저장
    if ((wri, wri) not in dp.keys()):
        dp[(wri, wri)] = dp[(wri, clip)] + 1
        q.append((wri, wri))
    # 2) 붙여넣기
    if ((wri+clip, clip) not in dp.keys()):
        dp[(wri+clip, clip)] = dp[(wri, clip)] + 1
        q.append((wri+clip, clip))
    # 3) 출력된 이모티콘 하나 삭제
    if ((wri-1, clip) not in dp.keys()):
        dp[(wri-1, clip)] = dp[(wri, clip)] + 1
        q.append((wri-1, clip))

print(dp[(wri, clip)])
