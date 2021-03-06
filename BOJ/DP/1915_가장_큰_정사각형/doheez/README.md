## Info

<a href="https://www.acmicpc.net/problem/1915" rel="nofollow">1915번: 가장 큰 정사각형</a>

## ❗ 풀이
좌측(←), 상측(↑), 좌상측(↖) DP 값 중 가장 작은 숫자에 1을 더함으로써 DP 값을 구하고, DP 이차원 배열의 최댓값을 구해 가장 큰 정사각형의 한 변 길이를 구할 수 있다. 넓이를 구해야 하므로 문제의 답으로는 가장 큰 정사각형의 한 변 길이 제곱을 출력한다.

단, 두 가지 예외 사항이 존재한다. 0번째 행과 0번째 열은 좌측, 상측, 좌상측 DP 값이 존재하지 않기 때문에 입력받은 map 배열의 값을 그대로 DP 값으로 가진다.

또한, map 값 자체가 0인 경우 좌측, 상측, 좌상측으로부터 정사각형이 이어지지 않으므로 DP 값 또한 0으로 설정한다.


## ❗ 추가 지식
`Math.min()` 함수의 인자로 2개의 값만 넣을 수 있다. 따라서 여러 개의 값을 비교하기 위해서는 min 함수를 중첩해서 사용한다.

## 🙂 마무리
전형적인 DP 문제였음에도 아직 낯설고 DP 배열 설정에서부터 헤매게 된다. 언제쯤 친해질 수 있을까...

참고한 블로그: https://kyun2da.github.io/2021/04/09/biggestSquare/