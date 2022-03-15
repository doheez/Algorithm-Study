N = int(input())

# dp[N][lastnum] -> 길이 N일 때, lastnum으로 끝나는 경우의 수.
# dp[N]을 계산할 때, dp[N-1]만 참조하면 되므로 dp배열의 크기는 2행으로 충분
dp = [[1]*10 for _ in range(2)]
dp[0][0] = 0 # 초깃값(길이가 1)에서 0으로 끝나는 경우의 수는 존재할 수 없으므로 dp[0][0] = 0

for _ in range(1, N):
    dp[1] = [dp[0][l+1] if (l == 0) else dp[0][l-1] if (l == 9) else dp[0][l+1] + dp[0][l-1] for l in range(10)]
    dp[0], dp[1] = dp[1], dp[0] # 리스트 스왑
print(sum(dp[0]) % 1000000000)
