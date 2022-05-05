import sys

adj_two = [[1, 2, 3, 4], [2, 3, 5], [4, 5], [4, 5], [5], []]
adj_tri = [[(1, 2), (1, 3), (2, 4), (3, 4)], [(2, 5), (3, 5)], [(4, 5)], [(4, 5)], [], []]
N = int(sys.stdin.readline())
dice = list(map(int, (sys.stdin.readline().split())))

if (N == 1):
    print(sum(dice) - max(dice))
else:
    minvalue = min(dice)

    min_twopair = sys.maxsize
    min_tripair = sys.maxsize
    for idx, num1 in enumerate(dice):
        for i in adj_two[idx]:
            min_twopair = min(min_twopair, num1+dice[i])
        for i, j in adj_tri[idx]:
            min_tripair = min(min_tripair, num1+dice[i]+dice[j])

    print(4*(min_tripair) + (8*N -12)*(min_twopair) + (5*N**2 - 16*N + 12)*(minvalue))
