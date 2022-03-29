import sys

N = int(sys.stdin.readline())
matrix = [list(map(int, list(sys.stdin.readline().strip()))) for _ in range(N)]


def isAllSame(l: list) -> bool:
    std = l[0][0]
    for row in l:
        for item in row:
            if (item != std):
                return False
    return True


def solution(m: list):
    half_size = len(m)//2
    # 모두 같은 데이터일 경우 0 또는 1로 압축
    if (isAllSame(m)):
        print(m[0][0], end="")
    # 현재 구역에 서로 다른 데이터가 공존할 경우 4개의 구역으로 분할
    else:
        print("(", end="")
        solution([m_div[0:half_size] for m_div in m[0:half_size]]) # 좌상
        solution([m_div[half_size:] for m_div in m[0:half_size]]) # 우상
        solution([m_div[0:half_size] for m_div in m[half_size:]]) # 좌하
        solution([m_div[half_size:] for m_div in m[half_size:]]) # 우하
        print(")", end="")


if __name__ == "__main__":
    solution(matrix)
