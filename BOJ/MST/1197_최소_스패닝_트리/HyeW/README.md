## Info

<a href="https://www.acmicpc.net/problem/1197" rel="nofollow">1197 최소 스패닝 트리</a>

## ❗ 풀이

간선들의 가중치를 오름차순으로 정렬하고, <br/>
작은 간선들부터 보면서 해당 간선을 넣었을 때 간선이 갖는 두 노드의 부모를 확인하여 사이클을 이루는지 확인하였다.<br/>
만약 부모가 다르면 사이클을 이루지 않으니 해당 간선을 Union하고
간선의 가중치를 더했다.<br/>
<br/>
n-1개의 간선이 union되면 최소 가중치를 갖는 Spaninng Tree을 구한 것으로 답을 출력한다. 
## ❗ 추가 지식

없음

## 🙂 마무리
최소 스패닝 트리를 공부하면서 크루스칼 알고리즘을 보는데
<a href="https://github.com/doheez/Algorithm-Study/tree/main/BOJ/Greedy/10775_%EA%B3%B5%ED%95%AD/HyeW" rel="nofollow"> 10775 공항 </a> 에서 사용했던 유니온 파인드를 써야하겠구나 라는 생각이 들었다.<br/>
공항 문제에서 유니온 파인드를 공부해서 Kruskal MST를 쉽게 이해할 수 있었다.