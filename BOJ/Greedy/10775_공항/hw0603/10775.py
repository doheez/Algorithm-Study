import sys

G = int(sys.stdin.readline())
P = int(sys.stdin.readline())
plist = [int(sys.stdin.readline()) for _ in range(P)]

gates = [0] * (G+1)  # Gate 수 만큼 리스트 생성

total = 0
for max_gate_no in plist:
    # 가장 큰 Gate 번호에서 시작하여 만약 그 Gate가 이용 가능하지 않다면
    # 번호를 점차 줄여 나가면서 이용 가능한 Gate 중 번호가 가장 큰 게이트를 찾을 것이다.
    # 이때, Gate 번호를 1씩 줄여 나가면 느리므로 i번째 Gate에 접근할 때 마다 카운트를 증가시켜 접근 횟수를 저장해 준다.
    # 그렇다면 i번째 Gate가 접근된 횟수는 그 게이트를 포함한 이전 i개의 Gate에 이미 비행기가 도킹되어 있다는 것을 의미한다.
    # 이를 이용하여 매 시행에서 Gate가 이용 가능하지 않을 때 마다 Gate[i]만큼 번호를 줄여 나가면서 탐색하면 시간을 절약할 수 있다.
    while (max_gate_no > 0 and gates[max_gate_no] > 0):
        t = gates[max_gate_no]  # 현재 게이트의 접근 횟수 저장
        gates[max_gate_no] += 1  # 접근 카운트 증가
        max_gate_no -= t  # 현재 게이트의 접근 횟수만큼 Gate 번호 감소

    # max_gate_no를 줄여 나갔는데 양수범위에서 모두 줄여도 도킹할 곳이 없는 경우
    if (max_gate_no <= 0):
        break
    else:
        gates[max_gate_no] = 1  # 사용 가능한 게이트 중 가장 큰 게이트에 도킹
        total += 1  # 총 도킹한 비행기 개수 증가

print(total)
print(gates)