## Info
<a href="https://www.acmicpc.net/problem/16234" rel="nofollow">16234 인구 이동</a>

## ❗ 풀이
먼저 그래프 전체를 BFS탐색하여 모든 연합을 찾은 뒤, `day`를 증가시키면서 조건에 맞게 인구 이동을 진행한다.
연합이 더 이상 형성될 수 없을 때(인구 이동이 없을 때) 루프를 탈출하고, 그 시점의 `day`를 출력하면 정답.

## ❗ 추가 지식
None

## 🙂 마무리
처음에 제출하자마자 틀렸습니다 뜨길래 뭔가 했더니 `bfs()` 호출 전에 visited 리스트를 초기화하지 않은 것이 원인이였다.  
기본적인 BFS문제 기반으로 연합을 어떻게 다룰지만 고민하면 되는 문제 같았지만 시간이 좀 빡세다. 
Python3으로 BFS 써서 풀었더니 계속 80%에서 시간 초과가 뜬다.  
시간의 압박으로 인해(...) 여러 풀이를 찾아보지는 못 했다. 아래 링크 두개 나중에 꼭 읽어보기.
<a href="https://aeroej.tistory.com/118" rel="nofollow">참고 1</a>
 | 
<a href="https://mygumi.tistory.com/338" rel="nofollow">참고 2</a>
