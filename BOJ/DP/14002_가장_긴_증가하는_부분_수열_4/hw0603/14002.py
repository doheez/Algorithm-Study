import sys

N = int(sys.stdin.readline())
arr = list(map(int, sys.stdin.readline().split()))

# dp[0][i]: arr[i]로 끝나는 가장 긴 증가하는 부분 수열의 길이.
# dp[1][i]: 그 부분 수열에서 arr[i] 바로 앞에 나오는 수의 인덱스
dp = [[1] * N , [-1] * N]

for idx in range(1, N):
    dp_max, idx_max = 0, -1 # idx_max는 0이 의미 있는 값이므로 -1로 초기화
    for i in range(idx):
        if (dp[0][i] > dp_max and arr[i] < arr[idx]):
            dp_max = dp[0][i]
            idx_max = i
    dp[0][idx] = dp_max + 1
    if (idx_max >= 0):
        dp[1][idx] = idx_max

length = max(dp[0])
endidx = dp[0].index(length)

print(length)
sub_arr = []
while (endidx != -1):
    sub_arr = [arr[endidx]] + sub_arr
    endidx = dp[1][endidx]
print(" ".join(map(str, sub_arr)))
