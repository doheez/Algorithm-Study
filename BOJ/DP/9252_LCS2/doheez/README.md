## Info

<a href="https://www.acmicpc.net/problem/9252" rel="nofollow">9252번: LCS 2</a>

## ❗ 풀이
LCS의 길이를 구하는 풀이는 9251번 LCS 문제와 같고, 이 문제에서는 LCS 자체를 출력하는 풀이가 추가되었다.

`dp[i][j]`를 dp 배열의 끝 부분부터 탐색한다. `str1[i-1]`과 `str2[j-1]`가 같다면 LCS 배열에 해당 문자를 추가하고 `i`, `j`를 1씩 감소시킨다.

만약 `str1[i-1]`과 `str2[j-1]`가 같지 않다면, `dp[i][j-1]`과 `dp[i-1][j]` 중 더 큰 것을 찾아서 해당 index를 따라 `i` 또는 `j`를 1만큼 감소시킨다.

이렇게 `i` 또는 `j`가 0에 도달할 때까지 반복하면 LCS 배열이 완성되고, 이를 마지막 index부터 출력하면 구하고자 하는 LCS가 출력된다.

## ❗ 추가 지식


## 🙂 마무리
`dp` 배열의 유의미한 index는 1부터 시작하고, `sequence[x]` 배열의 index는 0부터 시작하도록 설정해두었으므로 값을 비교할 때 꼭 주의해야 한다. 난 아무 생각 없이 문자열 배열도 index가 1부터 시작하는 것으로 착각하고 코드를 작성했다가 `undefined`를 만났다.