## Info

<a href="https://www.acmicpc.net/problem/10844" rel="nofollow">10844 쉬운 계단 수</a>

## ❗ 풀이
`dp[N][lastnum]`을 길이가 N일 때 lastnum으로 끝나는 경우의 수라고 하자.  
예를 들어, N=2 이고 lastnum=0 일 때, 십의 자리 숫자는 1이 올 수 밖에 없으므로 `dp[2][0]` 의 값은 1이 된다.  
  
그렇다면, 아래와 같은 점화식이 성립한다.  
`dp[N][l] = dp[N-1][l+1] if (l == 0) else dp[N-1][l-1] if (l == 9) else dp[N-1][l+1] + dp[N-1][l-1]`  
  
이때, N번째 시행의 결과를 구하기 위해 N-1번째 시행만 조사하고, 그 이전의 결과는 사용하지 않으므로 매 iteration 마다 `dp[0]` 과 `dp[1]`을 swap 해 주면, `dp[2][10]`의 고정된 크기의 리스트로 모든 결과를 처리할 수 있다.


## ❗ 추가 지식
iterable 한 타입의 원소를 더할 때, 루프로 순회하며 `+=` 연산을 하는 것 보다 `sum(iterable)` 을 수행하는 것이 몇 배 이상 빠르다. 루프는 파이썬 바이트코드로 처리되지만, `sum()` 함수는 내부적으로 C코드를 사용하기 때문.  
  
리스트를 생성할 때는 for 루프로 `append()` 하는 것 보다 list comprehension을 활용하는 것이 더 빠르다. 반대로, 
리스트를 생성하지 않고 계산을 수행하거나, 함수를 호출할 때는 `for` 루프가 더 빠르다.  
번외로, `list(range())` 와 같이 list 함수에 iterator를 전달해 주는 것이 리스트 생성 시에 루프와 list comprehension 보다 좋은 퍼포먼스를 보인다.

<a href="https://towardsdatascience.com/list-comprehensions-vs-for-loops-it-is-not-what-you-think-34071d4d8207">참고</a>

## 🙂 마무리
DP 문제 풀때 자꾸 loop를 순회하면서 "다음" 시행이 어떻게 될지에 집중하게 되는 경향이 있다.  
항상 현재가 N번째 시행일때 N-1번쨰 시행의 결과를 어떻게 활용할지를 잘 고민해 봐야 할 듯.
