## Info

<a href="https://www.acmicpc.net/problem/17142" rel="nofollow">17142 연구소 3</a>

## ❗ 풀이

최단시간을 구하는 문제로 bfs를 사용하였다.<br/>
우선 input값을 받을때 바이러스 위치와 개수, 0의 개수를 저장하였다.<br/>
바이러스 위치 정보를 가지고 조합 알고리즘으로 m개의 바이러스를 구하고,<br/>
bfs를 사용해 4방탐색으로 바이러스가 퍼지는 시간을 구하였다.<br/>

```java
while (q.size() > 0) {
    if (v_space == space) { // 0공간이 없으면 min찾고 함수 끝
        min = Math.min(min, cnt);
        return;
    }
    //...
    while (s-- > 0) {
        newq = q.poll();

        for (int i = 0; i < 4; i++) {
           //...
            if (room[dx][dy] == 1) // 벽일때
            {
                visited[dx][dy] = true;
                continue;
            }

            if (room[dx][dy] == 0)
                v_space++;
            visited[dx][dy] = true;
            q.add(new Node(dx, dy));
        }
    }
}
```
bfs함수는 위와 같은데,
바이러스를 퍼트릴 수 있는 칸이 0이면 v_space를 1 증가시켜<br/>
초기 0의 개수(space)와 바이러스를 퍼트린 0의 개수(v_space)가 같을때 더이상 탐색할 필요가 없어 탈출조건을 만들어 주었다.<br/>
<br/> 

## ❗ 추가 지식

input으로 주는 값들의 범위를 확인하자.<br/>
min과 max의 초기값 설정할때는 `Integer.MAX_VALUE`, `Integer.MIN_VALUE`를 사용하자.

## 🙂 마무리

기본 bfs 문제에 여러 조건이 많아 if문을 많이 쓰는 문제였다.<br/>
하지만 시간이 많이 걸렸는데 다른 거보다 큐 초기화와 
최솟값을 구하는 데 배열의 크기를 보지않고 min의 초기값을 작게 설정하여 몇 시간을 허비하였다.<br/>
다음번에는 이런 실수가 없도록 2번 확인해야겠다.<br/>
또한 `if (v_space == space)`을 `while (s-- > 0)` 앞에 넣느냐 뒤에 넣느냐에 따라 속도가 달라지는데
스터디때 알아볼 예정이다.