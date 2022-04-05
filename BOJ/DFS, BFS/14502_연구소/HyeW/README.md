## Info

<a href="https://www.acmicpc.net/problem/14502" rel="nofollow">14502 연구소</a>

## ❗ 풀이

1. input을 받아 배열 생성 시, 바이러스 위치를 vi배열에 저장하고 벽 개수 세어 저장하였다.
2. 0이 있는 자리에 조합을 이용해 벽세운다.<br/>
   조합 시 for문 하나를 써서 i범위를 n(row개수)*m(col개수)까지만 하고,
   x와 y의 좌표는 각각 i/m, i%m하여 구했다.
3. DFS를 사용해 바이러스가 퍼지는 것을 확인하고 다 퍼졌을 때 바이러스 수 구한다.
4. 2번과 3번을 반복하면서 바이러스 개수가 가장 적을 때를 구한다.
5. 전체 연구실 넓이에서 최소 바이러스 개수, 벽의 개수를 빼주면 최대 안전 영역의 넓이가 나온다.

## ❗ 추가 지식

조합 알고리즘을 공부하였다.
```java
static void comb(int start, int depth, boolean[] visited){
    if(depth == 0){
        print();
        return;
        }
    for(int i = start; i < n; i++){
        visited[i] = true;
        comb(i+1, r-1, visited);
        visited[i] = false;
        }
 }
```

## 🙂 마무리

매우 지저분하게 풀었다고 생각했는데 자바언어를 사용한 맞힌 사람중에 22등을 했다. 왜지?<br/>
~~바이러스 자리 저장한게 큰건가~~ <br/>
스터디때 다른 사람 코드를 보며 내가 무엇을 다르게 풀었는지 알아봐야겠다<br/>
