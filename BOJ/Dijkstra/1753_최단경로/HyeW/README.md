## Info

<a href="https://www.acmicpc.net/problem/1753" rel="nofollow">1753 최단경로</a>

## ❗ 풀이

방향 그래프로 인접리스트를 구현하여 문제를 해결했다.<br/>
다익스트라 구현 문제로 아래와 같이 우선순위 큐를 사용하여 시간 복잡도를 줄여주었다.<br/>

```java
PriorityQueue<Node> q = new PriorityQueue<>((o1, o2)-> Integer.compare(o1.cost,o2.cost));
q.offer(new Node(start,0));
dist[start] = 0;

while(!q.isEmpty()){
        Node curNode=q.poll();
    
        if(dist[curNode.idx]<curNode.cost)
            continue;
    
        for(int i=0;i<graph.get(curNode.idx).size();i++){
            Node nxtNode=graph.get(curNode.idx).get(i);
        
            if(dist[nxtNode.idx]>curNode.cost+nxtNode.cost){
                dist[nxtNode.idx]=curNode.cost+nxtNode.cost;
                q.offer(new Node(nxtNode.idx,dist[nxtNode.idx]));
            }
    }
}
```
먼저 간선 비용 기준으로 minHeap을 만들어 시작지점을 넣어준다.<br/>
큐를 통해 현재 dist중 가장 작은 값을 꺼내 Node를 탐색한다.<br/>
while문안에 `if(dist[curNode.idx]<curNode.cost)`는 visited 체크로 
현재 q에서 뽑은 `curNode.cost(= dist[i])`보다 이미 저장되어 있는 `dist[i]`가 더 작다면
curNode에 대해서 검사할 이유가 없기때문에 continue해준다.<br/>
<br/>
for문을 통해선 curNode에서 갈 수 있는 노드를 조사하여 
`if(dist[nxtNode.idx]>curNode.cost+nxtNode.cost)` 기존 값이 더 작은지, 현재 노드를 거쳐 가는 것이 더 작은지 비교를 하여 최소값을 찾는다.<br/>

## ❗ 추가 지식

`compare(o1,o2)`함수 : o1 - o2 가 음수 이면 -1, 0이면 0, 양수면 1을 리턴한다. <br/>
`ArrayList<ArrayList<Object>> graph = new ArrayList<>();` : 이차원 List만드는 방법이다.<br/>

## 🙂 마무리

알고리즘 수업을 들을때 다익스트라 분명 쉽게 이해를 했었는데...<br/>
다시 공부를 하니 이론은 이해가 되는데 코드로 우선순위 큐를 사용해서 보니 
헷갈려서 몇시간 동안 코드만 봤다<br/>