import sys 

N = int(sys.stdin.readline())
arr = list(map(int, sys.stdin.readline().split()))
dp = [1] * N

def solution():
    for idx in range(1, N):
        dp_max = 0
        for i in range(idx):
            if (dp[i] > dp_max and arr[i] < arr[idx]):
                dp_max = dp[i]
        dp[idx] = dp_max + 1

if __name__ == "__main__":
    solution()
    print(max(dp))
