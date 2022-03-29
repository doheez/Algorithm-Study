import sys

str1 = sys.stdin.readline().strip()
str2 = sys.stdin.readline().strip()
str1_len = len(str1)
str2_len = len(str2)

dp = [[0] * (str2_len+1) for _ in range(str1_len+1)]

for i in range(1, str1_len+1):
    for j in range(1, str2_len+1):
        if (str1[i-1] == str2[j-1]): # 새로 추가한 문자가 같은 경우
            dp[i][j] = dp[i-1][j-1] + 1 # 이번 시행에서 추가된 문자를 제외한 문자열들의 LCS에서 1을 더함
        else: # 추가된 두 문자가 다른 경우
            dp[i][j] = max(dp[i-1][j], dp[i][j-1]) # 각 문자들을 하나씩 제외한 문자열들의 LCS 길이 중 최댓값을 가짐

length = dp[-1][-1] # LCS 길이

row, col = str1_len, str2_len
pos = length # 인덱스를 찾을 LCS의 문자 번호
lcs_index = [] # LCS를 구성하는 문자들의 인덱스

# row 혹은 col 둘 중 하나가 0이 될 때 까지 마지막 결과로부터 거슬러 올라가면서 원본 LCS 복원
while (row and col):
    # 두 문자가 같을 경우 (행-1), (열-1)에서 이어지는 수열임
    if (str1[row-1] == str2[col-1]):
        row -= 1
        col -= 1
    # 두 문자가 다를 경우 (행-1), (열) 또는 (행), (열-1) 에서 이어지는 수열임. 둘 중에 큰 것 취함
    else:
        if (dp[row-1][col] > dp[row][col-1]):
            row -= 1
        else:
            col -= 1
    # row, col을 복원시킨 이후 lcs길이가 달라졌을 때의 인덱스 저장
    if (dp[row][col] < pos):
        pos = dp[row][col]
        lcs_index.append((row, col))

print(length)
# stack을 사용해야 하므로 리스트를 거꾸로 출력
print("".join([str1[row] for row, _ in reversed(lcs_index)]))
#print("".join([str2[col] for _, col in reversed(lcs_index)])) # 두 가지 방법 모두로 구할 수 있음