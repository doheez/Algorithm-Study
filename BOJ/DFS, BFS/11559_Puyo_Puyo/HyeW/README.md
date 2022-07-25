## Info
<a href="https://www.acmicpc.net/problem/11559" rel="nofollow">11559 Puyo Puyo</a>

## ❗ 풀이

1. 터질 뿌요가 있는지 전체 판을 같은 색을 기준으로 bfs로 검사한다.
   1. bfs하면서 뿌요 좌표를 리스트에 저장한다.
   2. 만약 리스트 길이가 4이상이라면 이번 턴에 터질 뿌요로 전역 리스트에 옮겨 닮는다.
2. 터질 뿌요를 다 찾았다면 뿌요를 터트린다.
   1. 터질 뿌요를 리스트에서 하나씩 꺼내서 현재 뿌요 x좌표에서 `(0,y)` 또는 `.`
일 때까지 -1하면서 하나씩 자리를 아래로 옮겨준다.
3. 터질 뿌요가 없을때 까지 1, 2번을 반복한다.

## ❗ 추가 지식

없음

## 🙂 마무리

구현문제를 너무 어렵게 풀려고 하는게 문제다.<br/>
문제를 보고 1단계까지 생각하고 문제를 풀어야하는데 한 번에 최적화 방법까지 3단계를 바로 생각해서
풀려고 하니 오류도 많고 코드를 몇번 고친지 모르겠다..<br/>
차근차근 생각해서 코드가 더럽게 느껴져도 바로 푸는 버릇을 가져야겠다<br/>