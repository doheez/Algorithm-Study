## Info

<a href="https://www.acmicpc.net/problem/2178" rel="nofollow">2178 미로 탐색</a>

## ❗ 풀이

최단 경로를 구하는 문제로 BFS를 사용하였다.
미로에서 한칸 이동을 알기 위해선 자식 노드로 이동할때 +1을 하여 거리를 구할 수 있다.
```java
while (q.size() > 0){
    s=q.size();

    while(s-->0){
        Node temp=q.poll();
        //...
    }
    cnt++;
}
```
s에 현재 큐사이즈(같은 라인인 형제 노드 개수)를 담는다.<br/> 
q.poll()을 한번 할때마다 s--하여 0이 되었을 때(1 depth 탐색이 끝났을 때) 미로 한칸을 나아갔다고 볼 수 있다.
<br/>
그리고 큐에 값이 남아있어도 최단거리를 구하기 때문에 목적지에 도착하면 BFS를 끝낸다.
## ❗ 추가 지식

자바에서 큐를 사용하려면 변수 타입은 'Queue'로 선언하고 
생성 시 'new LinkedList<>()'를 이용해 만든다.

## 🙂 마무리

예전에 미로문제와 풀이가 유사한 
<a href="https://www.acmicpc.net/problem/7576" rel="nofollow">토마토 문제</a>
를 풀어서 이번에 쉽게 풀었다.
