## Info

<a href="https://www.acmicpc.net/problem/16236" rel="nofollow">16236 아기상어</a>

## ❗ 풀이

BFS 문제에 조건 판별이 아주아주 빡셌던 문제<br>
완전탐색 최악의 경우 400 * 400 정도로 완전 탐색을 하라고 나온 문제란걸 알 수 있었다.<br>
매 완전 탐색마다 최적의 먹이 위치를 찾고, 찾았으면 먹으러 가면 된다.<br>
distance 를 체크하고 visited 를 같이 체크했어야하는데 distance 가 있다는건 visited 라는 의미를 나타낸다.<br>

## ❗ 추가 지식

Integer.MAX_VALUE : 32비트 int 최대값

## 🙂 마무리


