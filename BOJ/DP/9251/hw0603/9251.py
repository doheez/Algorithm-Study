import sys

str1 = [ord(s) for s in list(sys.stdin.readline().strip())]
str2 = [ord(s) for s in list(sys.stdin.readline().strip())]

len1 = len(str1)
len2 = len(str2)

dp = [[0] * (len2 + 1) for _ in range(len1 + 1)]

for i in range(1, len1+1):
    for j in range(1, len2+1):
        if (str1[i-1] == str2[j-1]): # 새로 추가한 문자가 같은 경우
            dp[i][j] = dp[i-1][j-1] + 1 # 이번 시행에서 추가된 문자를 제외한 문자열들의 LCS에서 1을 더함
        else: # 추가된 두 문자가 다른 경우
            dp[i][j] = max(dp[i-1][j], dp[i][j-1]) # 각 문자들을 하나씩 제외한 문자열들의 LCS 길이 중 최댓값을 가짐

print(dp[-1][-1]) # dp배열의 마지막 원소 출력
