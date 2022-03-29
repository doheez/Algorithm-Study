from re import L
import sys

N, K = map(int, sys.stdin.readline().split())

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
arr = [[0, 0]] + arr # arr 리스트도 dp와 같이 1번 인덱스부터 데이터 담기
dp = [[0]*(K+1) for _ in range(N+1)] # dp[i][j] = i(>0)번째 물건까지 조사했을 때, 무게 j로 만들 수 있는 가치의 최댓값

for i in range(1, N+1):
    for j in range(1, K+1):
        if (j < arr[i][0]): # 무게 제한값이 현재 물건의 무게보다 작으면
            dp[i][j] = dp[i-1][j] # 넣지 않음
        else: # 무게제한이 현재 물건보다 커서 적어도 현재 물건 하나는 들어갈 수 있는 경우
            dp[i][j] = max(dp[i-1][j], dp[i-1][j - arr[i][0]] + arr[i][1]) # 넣는 경우와 넣지 않는 경우 둘 중 가치가 높은 경우를 택함


print(dp[-1][-1])