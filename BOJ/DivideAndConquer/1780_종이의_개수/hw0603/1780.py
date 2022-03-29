import sys

N = int(sys.stdin.readline())
paper = []

result = [0, 0, 0]

for _ in range(N):
    paper.append(list(map(int, sys.stdin.readline().split())))


def isAllSame(l: list) -> bool:
    std = l[0][0]
    for row in l:
        for item in row:
            if (item != std):
                return False
    return True


def solution(p: list):
    length = len(p)
    # 현재 종이에 적힌 숫자가 모두 같은 경우
    if (isAllSame(p)):
        result[p[0][0]] += 1 # 그 숫자에 맞는 결과를 1 증가
        return
    # 모두 같은 숫자가 아닌 경우 9개로 분할하고 반복
    else:
        div_length = length//3 # 구역 분할 후 한 변의 길이
        for i in range(0, length, div_length):
            for j in range(0, length, div_length):
                div_paper = [p[i+k][j:j+div_length] for k in range(div_length)]
                solution(div_paper)


if __name__ == "__main__":
    solution(paper)
    print(f"{result[-1]}\n{result[0]}\n{result[1]}")
