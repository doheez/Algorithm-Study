import sys
sys.setrecursionlimit(10**8)

N, M = map(int, sys.stdin.readline().split())
relation = [[] for _ in range(N)]
count = 0

for _ in range(M):
    p1, p2 = map(int, sys.stdin.readline().split())
    relation[p1].append(p2)
    relation[p2].append(p1)

def dfs(num, friendlink):
    if (len(friendlink) >= 4):
        print(1)
        sys.exit()
    
    friendlink.append(num)
    
    # 현재 조사 대상인 사람의 모든 친구에 대해
    for p in relation[num]:
        # 지금까지 조사하지 않은 새로운 친구라면
        if (p not in friendlink):
            dfs(p, friendlink[:])
        

for i in range(N):
    dfs(i, [])
print(0)