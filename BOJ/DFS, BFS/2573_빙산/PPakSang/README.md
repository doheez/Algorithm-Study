## Info
<a href="https://www.acmicpc.net/problem/2573" rel="nofollow">2573 빙산</a>

## ❗ 풀이

1. 각 빙하 별로 주변 바다의 수를 세고
2. 바다의 수 만큼 빙하를 녹이는데, 이 때 빙하가 다 녹는다면 days*-1 로 체킹을 해주었다.
3. 따로 체킹을 해 준 이유는 당일날 녹은 빙하가 다른 빙하에게 영향을 주면 안되기 때문에
4. 다 녹인 후에는 다시 빙하를 체크하면서 dfs 로 군집을 파악하고, 군집이 2개이상 있다면 값을 출력
5. 확인을 해봤는데 빙하가 한 덩이도 없으면 0 출력



## ❗ 추가 지식


## 🙂 마무리

날짜별로 빙하를 체크해주려고 -1을 곱해주는 방식을 선택했는데, 다른 풀이를 찾아보니 당일 날에만 영향을 안주면 돼서 visited 로
각 날짜마다 체킹할 변수를 따로 지정해주는게 의미상 더 맞는 풀이가 되겠다.  


메소드 끼리 이차원 배열을 넘기는게 상당히 꺼려지는데, 재귀문으로 돌게 아니라면 너무 지양하지 않아도 될 것 같다.