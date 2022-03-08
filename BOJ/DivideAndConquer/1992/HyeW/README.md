## Info

<a href="https://www.acmicpc.net/problem/1992" rel="nofollow">1992 쿼드트리</a>

## ❗ 풀이

재귀호출을 하는 경우는 주어진 배열이 다 같은 수가 아닐 때 뿐이니 그 부분만 따로 `if(first != map[i][j])`을 이용해 처리해 주었다

`void recur(int row, int col, int n)`<br/>
배열을 4등분하는 함수의 인자로 종이의 첫 번째 시작 행, 시작 열, 배열의 행의 길이로 주어 따로 배열을 주지 않아도 처리할 수 있도록 하였다

## ❗ 추가 지식

java에서 string을 char형 배열로 받는 함수는 `toCharArray()`이고 string에서 한 글자만 선택하여 char로 변환해주는 함수는 `charAt(int index)`이다


## 🙂 마무리

1780 종이의 개수와 문제 유형이 완전 똑같아서 풀기 수월했다<br/>
이 문제에서 내가 주의했어야하는건 input값을 숫자로 변환안하고 문자열로 읽어야한다는 거?<br/>

