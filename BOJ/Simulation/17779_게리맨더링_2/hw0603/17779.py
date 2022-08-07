import sys

N = int(sys.stdin.readline())
matrix = [[0 for _ in range(N+1)]]
total = 0  # 시 내의 전체 인구 수

# 데이터 입력받으면서 전체 인구 수 구함
for i in range(N):
    line = [0] + list(map(int, input().split()))
    total += sum(line)
    matrix.append(line)


# x, y, d1, d2를 전달받고 선거구 쪼갠 뒤 최대/최소 선거구의 인구 수 차이 반환
def divide(x, y, d1, d2):
    g = [[0]*(N+1) for _ in range(N+1)]
    population = [0, 0, 0, 0, 0]  # 선거구 별 인구 수

    # 경계선 생성
    g[x][y] = 5
    for i in range(1, d1+1):
        g[x+i][y-i] = 5
    for i in range(1, d2+1):
        g[x+i][y+i] = 5
    for i in range(1, d2+1):
        g[x+d1+i][y-d1+i] = 5
    for i in range(1, d1+1):
        g[x+d2+i][y+d2-i] = 5

    # 1~4번 선거구를 반복문으로 구하기 위한 range() 리스트
    a = [range(1, x+d1), range(1, x+d2+1), range(x+d1, N+1), range(x+d2+1, N+1)]
    b = [range(1, y+1), range(N, y, -1), range(1, y-d1+d2), range(N, y-d1+d2-1, -1)]

    # 1~4번 선거구
    for k in range(4):
        for i in a[k]:
            for j in b[k]:
                if (g[i][j] == 5):
                    break
                else:
                    population[k] += matrix[i][j]

    # 전체 인원에서 1~4번 선거구 인구 수를 빼면 5번 선거구
    population[4] = total - sum(population[0:4])
    
    return max(population) - min(population)


# 브루트포스
result = sys.maxsize
for x in range(1, N+1):
    for y in range(1, N+1):
        for d1 in range(1, N+1):
            for d2 in range(1, N+1):
                if (x+d1+d2 > N or y-d1 < 1 or y+d2 > N):  # 조건에 맞지 않는 값 skip
                    continue
                result = min(result, divide(x, y, d1, d2))

print(result)
