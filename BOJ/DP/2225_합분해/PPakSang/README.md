## Info

<a href="https://www.acmicpc.net/problem/2225" rel="nofollow">2225 합분해</a>


## ❗ 알고리즘

숫자 n 을 k 개의 합으로 나타내는 문제

k 번 째 숫자에 k~t~0 을 각각 넣으면  

문제는 숫자 n-t 을 k-1 개의 합으로 나타내는 문제로 바뀐다  

이는 n-1 을 k 개의 합으로 나타내는 문제 + n 을 k-1 개의 합으로 나타내는 문제와 같으므로    

점화식 dp[n][k] = dp[n-1][k] + dp[n][k-1] 로 표현 가능하다.  


## ❗ 알고리즘 외

없음

## 🙂 정리

없음