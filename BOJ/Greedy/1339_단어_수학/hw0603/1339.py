import sys

N = int(sys.stdin.readline())
word_list = sorted([list(sys.stdin.readline().strip()) for _ in range(N)], key=lambda x: len(x), reverse=True)
ch2num = {chr(65+i): None for i in range(26)}
weight = {chr(65+i): 0 for i in range(26)}

# 각 문자별로 가중치 구함
for word in word_list:
    for i in range(len(word)):
        weight[word[i]] += 10 ** (len(word)-i-1)

# 가중치가 높은 순서대로 매핑 테이블 구축
num = 9
for w in sorted(weight.items(), key=lambda x: x[1], reverse=True):
    if (w[1]):
        ch2num[w[0]] = str(num)
        num -= 1

# 문자열 매핑 후 합 계산
print(sum([int("".join(list(map(lambda ch: ch2num[ch], word)))) for word in word_list]))
