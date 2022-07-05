## Info

<a href="https://www.acmicpc.net/problem/14226" rel="nofollow">14226 이모티콘</a>

## ❗ 풀이

1초가 걸리는 연산은
1. 클립보드에 저장하기
2. 클립보드에 저장된 것 붙여넣기
3. 하나 삭제하기

이다.<br/>

bfs를 돌면서 해당 1,2,3 연산들을 모든 경우에서 하나하나 다 해보는 것인데,
<br/>
visited배열을 2차원 배열로 화면에 나타난 이모티콘의 개수가 col, 클립보드에 저장된 이모티콘의 개수 row로 설정하면 
쉽게 풀린다.<br/>
1. Queue의 타입은 현재 이모티콘의 개수, 클립보드에 저장된 이모티콘 개수, 초(second)를 가진 클래스이다.<br/>
2. bfs를 돌면서 현재 이모티콘 상황에서 1,2,3을 연산하여 큐에 넣어준다.<br/>
```java
// 1. 임티 클립보드에 복사
if (!visited[cur.cnt][cur.cnt]) {
    q.add(new Emoji(cur.cnt, cur.sec + 1, cur.cnt));
    visited[cur.cnt][cur.cnt] = true;
}
// 2. 클립보드에 있던거 붙이기
if (cur.clip != 0 && cur.cnt + cur.clip <= S && !visited[cur.clip][cur.clip + cur.cnt] ) {
    q.add(new Emoji(cur.cnt + cur.clip, cur.sec + 1, cur.clip));
    visited[cur.clip][cur.clip + cur.cnt] = true;
}

// 3. 삭제
if (cur.cnt != 0 && !visited[cur.clip][cur.cnt - 1]) {
    q.add(new Emoji(cur.cnt - 1, cur.sec + 1, cur.clip));
    visited[cur.clip][cur.cnt - 1] = true;
}
```
3. bfs를 돌다가 원하는 수 만큼 이모티콘이 입력되면 답을 출력하고 return 하여 bfs를 끝낸다.

여기서 가장 적은 시간을 찾는 문제인데 우선순위 큐가 아니라 그냥 연결리스트를 쓴 이유는 다음과 같다.<br/>
모든 연산은 1초이기 때문에 bfs를 한바퀴씩 돌면서 q에 저장되는 시간은 1초에서 점점 2, 3, 4로 차례대로 커진다.
<br/>그래서 queue를 우선순위 큐가 아니라 그냥 linkedlist로 사용해도 된다.
또한 false일때 방문 한 것이 가장 적은 시간을 소요한 것이므로 true로 되어있을땐 q에 저장하지 않고 넘어가도 된다.


## ❗ 추가 지식

없음

## 🙂 마무리

이게 dp문제..?<br/>
이번에 dp배열을 어떻게 설정해야 할지는 좀 알았는데 bfs를 써야 한다는 생각을 못했다.<br/>
그리고 vistied를 메모이제이션으로 쓴다는 것이 처음 접해보는 유형이라서
머리로는 이해하는데 가슴으로 이해하기 조금 힘들었다.<br/>
하지만 풀이는 완전 간단했다.<br/>