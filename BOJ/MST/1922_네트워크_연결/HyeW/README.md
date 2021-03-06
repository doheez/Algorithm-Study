## Info

<a href="https://www.acmicpc.net/problem/1922" rel="nofollow">1922 네트워크 연결</a>

## ❗ 풀이

프림 알고리즘은 정점을 기준으로 가중치가 적은 간선들을 구하는 방법이다.<br/>
<br/>
인접리스트를 만들 때 무방향 그래프로 양 노드에 각각의 가중치를 넣어준다.<br/>

#### 프림 알고리즘
우선 순위 큐에 아무 노드 하나를 넣어 while 문을 시작한다.<br/>
poll한 노드의 인접리스트를 보며 방문할 다음 노드를 고른다.<br/>
다음 노드를 볼 때는 1. 방문하지 않았으며 2. 해당 노드로 가는데 현재까지 나온 최소 가중치보다 작아야 
방문 고려 대상이 된다.<br/>
poll했을 때 방문하지 않은 노드라면 MST를 이루고 있는 간선이므로 answer에 해당 노드의 가중치를 더하여 답을 구한다.<br/>

## ❗ 추가 지식

없음

## 🙂 마무리

이번엔 프림 알고리즘으로 풀어봤는데 크루스칼이 유니온 파인드만 알면 되니 더 간단한 것 같다.<br/>
프림 알고리즘은 BFS를 이용한 알고리즘으로 우선순위 큐를 사용하며 visited 배열도 있어야 하고 최소 가중치를 찾기 위한 배열도 필요해
크루스칼보다 복잡한 느낌이 든다.<br/>