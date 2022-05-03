## Info

<a href="https://www.acmicpc.net/problem/10026" rel="nofollow">10026 적록색약</a>

## ❗ 풀이

1. 먼저 적록색약이 아닌 사람이 봤을 때를 dfs로 구하고,<br/>
2. 적록색약인 사람이 봤을 때는 그림의 'G'와 'R'이 같도록 배열은 수정한 후<br/>
1과 같은 dfs함수로 구역의 수를 구하였다.

## ❗ 추가 지식

## 🙂 마무리

문제는 쉬웠다. <br/>
이번 문제에서 dfs의 포인트는 `if (map[dx][dy] != map[x][y]) continue;`로
현재 요소와 인접한 요소가 다르면 dfs에 들어가지 않는 다는 것이다.