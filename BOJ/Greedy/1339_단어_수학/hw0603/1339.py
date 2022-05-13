import sys

N = int(sys.stdin.readline())
word_list = sorted([list(sys.stdin.readline().strip()) for _ in range(N)], key=lambda x: len(x), reverse=True)
ch2num = {chr(65+i): None for i in range(26)}

# 매핑할 문자 순서 구함
num = '9'
for i in range(8, 0, -1):  # 수의 길이는 최대 8.
    for word in word_list:  # 모든 단어들을 순회
        if (len(word) - i >= 0):  # 길이가 긴 숫자의 앞자리부터 data에 추가
            current_ch = word[-i]
            if not (ch2num[current_ch]):  # 매핑되지 않은 데이터가 들어오면
                ch2num[current_ch] = num  # 매핑 데이터 추가
                num = str(int(num)-1)
            word[-i] = ch2num[current_ch]  # 문자 매핑

print(ch2num, word_list, sep='\n')

print(sum(map(lambda l: int("".join(l)), word_list)))