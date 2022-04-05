## Info

<a href="https://www.acmicpc.net/problem/2178" rel="nofollow">2178 미로 탐색</a>

## ❗ 풀이

DFS, BFS 중 BFS 를 선택해야하는 문항<br>
왜 BFS 를 선택하냐고 묻는다면 dfs+visited 조합은 한 지점에 대히여 도착할 수 있는 모든 경우를 탐색하는 것이고<br>
bfs+visited 조합은 한 지점을 최초(가장 적은 이동)로 도착하는 때가 언제인지 알 수 있다.<br>
어떻게든 해만 구하면 될 때는 DFS 가 유리할지 모르겠지만, 최적의 해를 구할 때는 BFS 를 떠올릴 필요가 있다.<br>

## ❗ 추가 지식

Character.getNumericValue(char) -> int

## 🙂 마무리

재귀를 통해 dfs 를 구현할 수 있고, queue 를 통해 bfs, stack 을 통해 dfs 를 구현할 수 있다.<br> 
bfs 는 최소 이동(순서와 관련된 모든 명사) 을 위한 것으로 볼 수 있고, <br>
dfs 는 최초 지점(문제의 첫 상황) 으로부터 빠르게 멀리 벗어날 수 있다는 장점이 있다.
