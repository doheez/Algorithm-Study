## Info

<a href="https://www.acmicpc.net/problem/2178" rel="nofollow">2178 미로 탐색</a>

## ❗ 풀이
(0, 0) 지점부터 4방향으로 BFS 탐색을 진행하며 maze 배열에 (이전 maze 배열의 값 + 1) 을 저장한다.  
이때 `maze[i][j]`의 값은 (0, 0)에서 이동하여 해당 지점까지 도달할 수 있는 최소한의 이동 횟수가 되므로, `maze[-1][-1]`을 출력하면 된다.


## ❗ 추가 지식



## 🙂 마무리


