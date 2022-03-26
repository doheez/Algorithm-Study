import sys

N, K = list(map(int, sys.stdin.readline().split()))

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [[0, 0] for _ in range(N)] # dp[i] = arr[i]를 마지막으로 포함하는 최선의 경우

dp[0] = arr[0]

for i in range(1, N):
    # 지금까지 최선의 경우 + 현재 물건 => 이 되어야 하는데...
    # 지금까지 가치가 가장 높은 경우의 무게 + 현재 물건이 무게 제한을 넘지 않으면 큰 상관이 없다. 그냥 바로 더하면 그게 최선의 경우가 됨 
    # 근데 제한을 넘어 갈 경우에는... 아니지. 제한을 미리 걸어야 하나? (arr[i]가 추가됐을 때 제한을 넘지 않는 애들 중에서, 가치의 최댓값을 가지는 애들 중에서, 무게가 가장 낮은거) + 현재 물건

    value_max = -1
    weight_min = 100001
    value_max_idx = None
    for j in range(0, i): # dp[0]부터 dp[i-1]까지의 경우 탐색
        if (dp[j][0] + arr[i][0] <= K): # 합쳐서 무게 제한을 넘지 않는 경우
            v = dp[j][1] + arr[j][1] # 현재 경우에 해당하는 조사할 가치
            if (v > value_max): # 합쳐서 가치의 최댓값보다 큰 경우
                value_max = v # 가치 최댓값 업데이트
                weight_min = dp[j][0] + arr[i][0] # 무게 최솟값 업데이트
                value_max_idx = j # 가치 최댓값에 해당하는 인덱스 저장
            elif (v == value_max):
                if (w := (dp[j][0] + arr[i][0]) < weight_min): # 가치가 최댓값이랑 같을 경우, 무게가 더 낮은 경우가 있을 수 있으므로 체크 
                    weight_min = w # 무게 최솟값 업데이트
                value_max_idx = j # 가치 최댓값에 해당하는 인덱스 저장
            else: # 가치의 최댓값에 미치지 못하는 경우
                pass # 최선의 경우가 아니므로 스킵
    
    ### --> 현재 버그: 합쳐서 무게 제한을 넘지 않는 경우가 없을 때, 최선의 경우가 안 구해짐

    if (value_max == -1): # 이전 기록들을 모두 다 조사했는데도, (현재 물건이 너무 무거워서) 제한 범위 이내의 경우가 없는 경우
        if (arr[i][0] <= K): # 현재 물건만 넣는데.. 현재 물건 하나의 무게가 제한 무게 안일때만 넣음
            dp[i] = arr[i] # 일단 현재 물건만 넣는데.. 현재 물건 자체도 무게를 초과할 경우는 예외처리가 필요함
        else: # 현재 물건 하나도 너무 무거워서 제한 무게 초과일 경우
            dp[i] = arr[i] # 근데 그냥 넣어줘도 어차피 탐색할 때 무게 초과 나니까 고려 대상이 아니네
            dp[i][1] = -1 # 근데 또 가치 맥스 출력할 때는 먹힐수도 있어서 예외처리
    else:
        bestidx = value_max_idx

        dp[i][0] = dp[bestidx][0] + arr[i][0] # 무게
        dp[i][1] = dp[bestidx][1] + arr[i][1] # 가치

print(arr)
print(dp)
print(max(dp, key=lambda x: x[1])[1])