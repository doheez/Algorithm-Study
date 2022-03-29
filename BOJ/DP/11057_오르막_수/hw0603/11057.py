N = int(input())
dp = [[1] * 10 for _ in range(N+1)] # dp[N][i] = 길이가 N인 i로 끝나는 오르막 수의 개수. (dp[0]은 사용 x)

for n in range(2, N+1):
    for i in range(1, 10):
        dp[n][i] = dp[n][i-1] + dp[n-1][i]

print(sum(dp[N]) % 10007)