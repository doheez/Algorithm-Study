## Info

<a href="https://www.acmicpc.net/problem/16236" rel="nofollow">16236번: 인구 이동</a>

## ❗ 풀이
1. A 배열 전체에 대해 bfs 탐색을 하며 현존하는 모든 연합을 찾는다.
2. 찾은 연합들에 대해 하루 동안 인구 이동을 진행한다.
3. 날짜를 증가시킨다.
4. 연합이 더이상 생기지 않을 때까지 1, 2, 3번을 반복한다.

## ❗ 추가 지식


## 🙂 마무리
처음엔 인구 이동을 할 때마다 visited 배열을 초기화해주지 않아서 에러가 났고, 이후엔 visited 표기 시점을 연합 조건 검사 전으로 잘못 잡아서 오답이 나왔다. bfs는 visited를 능숙하게 잘 다루는 것이 관건인 것 같다.