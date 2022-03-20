## Info

<a href="https://www.acmicpc.net/problem/9251" rel="nofollow">9251번: LCS</a>

## ❗ 풀이
첫 번째 수열을 `str1`, 두 번째 수열을 `str2`라고 했을 때 `i-1`번째 index를 끝으로 하는 `str1`의 부분 수열과 `j-1`번째 index를 끝으로 하는 `str2`의 부분 수열의 LCS 길이를 `dp[i][j]`라고 하자.

`str1[i-1]`과 `str2[j-1]`이 같다면, 이때의 LCS는 `dp[i-1][j-1]`에서 원소 하나가 추가되는 것이기 때문에 다음과 같이 표현할 수 있다.

> `dp[i][j] = dp[i-1][j-1] + 1`

`str1[i-1]`과 `str2[j-1]`이 같지 않다면, 이때의 LCS는 `str1[i-2]`과 `str2[j-1]`의 LCS와, `str1[i-1]`과 `str2[j-2]`의 LCS 중 더 긴 것에서 새 원소를 추가하지 않는 것이므로 다음과 같이 표현할 수 있다.

> `dp[i][j] = MAX(dp[i-1][j], dp[i][j-1])`

따라서, 이중 for문으로 dp 배열을 bottom-up 탐색하여 `dp[N1][N2]`를 구하면 `str1`, `str2`의 LCS 길이를 알 수 있다.

## ❗ 추가 지식
문자열을 입력받고서 길이를 구하려 할 때, 양 끝 공백 제거를 하지 않으면 `\n`도 하나의 문자로 인식되어 길이에 포함되니 꼭 제거 후 사용하자.

## 🙂 마무리
문제 풀이 시 참고한 글: https://st-lab.tistory.com/139