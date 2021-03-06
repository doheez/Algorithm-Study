## Info

<a href="https://www.acmicpc.net/problem/17070" rel="nofollow">17070 파이프 옮기기1</a>

## ❗ 풀이

dirCol, dirRow 로 이동을 표현했고, index 는 상태를 의미한다 (1=가로, 2=세로, 3=대각선)

visited 배열을 통해 dp를 구현하였고, row, col 에 state 로 도착했을 때

목적지로 갈 수 있는 경우의 수를 의미한다

dp+dfs 를 적용하였는데 이것이 가능한 이유는 도착한 (칸, 상태) 에서 항상 같은 방식으로 다음 경로로 진행하기 때문에

각 상태별로 벽을 체크해야하는 방식과 이동할 수 있는 방법이 다르기 때문에 이에 대한 처리가 필요하다

각 칸에서 탐색이 끝났다는 것은 해당 (칸, 상태) 에서의 경우의 수를 모두 구한 것이 된다. 따라서 해당 값을 반환한다.

## ❗ 추가 지식

## 🙂 마무리
