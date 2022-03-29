## Info

<a href="https://www.acmicpc.net/problem/11053" rel="nofollow">11053 가장 긴 증가하는 부분 수열</a>

## ❗ 풀이
`dp[i]`를 `arr[i]`로 끝나는 가장 긴 증가하는 부분수열의 길이라고 정의하자.  
그렇다면 정답은 dp 배열의 원소 중 최댓값이 될 것이다.  
문제에서 "증가하는" 부분수열에 대해 구하고 있으므로 `dp[idx]`의 값은 `arr[idx]`보다 작은 `arr[i]`들에 대하여, 모든 가능한 `dp[i]`의 경우 중 최댓값에 1을 더한 값이 된다.  
`dp[N-1]`이 구해질 때 까지 메인 반복문을 순회하고, 이때 도출된 `dp` 배열 중 최댓값이 정답.

## ❗ 추가 지식
timeit 모듈로 상세 시간 측정을 해 본 결과,  
```
smaller_list = [dp[i] for i in range(idx) if arr[i] < arr[idx]]
dp[idx] = max(smaller_list) + 1 if smaller_list else 1
```
위와 같이 list comprehension을 통해 코드를 간략하게 쓴 경우보다, 
```
dp_max = 0
for i in range(idx):
    if (dp[i] > dp_max and arr[i] < arr[idx]):
        dp_max = dp[i]
dp[idx] = dp_max + 1
```
이처럼 for문을 사용하여 풀어 적는 것이 더 빨랐다. 리스트를 직접 생성하지 않아도 되는 것에서 오는 차이라고 생각된다.

## 🙂 마무리
처음에 N번째 시행해서 N-1번째의 데이터만을 활용하면 된다고 생각하고 접근하여 잘못된 방법으로 한참을 헤맸던 문제.  
동적 계획법은 이전 시행들의 데이터를 현재 시행에서 활용한다는 것이지, 꼭 한 단계 이전의 데이터만을 활용한다는 생각을 버리자!
