## Info

<a href="https://www.acmicpc.net/problem/1916" rel="nofollow">1916 최소비용 구하기</a>

## ❗ 풀이

기본 다익스트라에서 while문안에 `if(curNode.city == end) break;` 해당 조건을 추가해주었다.
<br/>
우선순위 큐를 통해 작은 값을 먼저 뽑는다. 
그래서 처음 end 도시가 나오면 더 검사할 필요가 없기때문에 바로 while문을 나와 답을 출력해주면 된다.

## ❗ 추가 지식

없음

## 🙂 마무리

처음에 continue를 break로 잘못 적고 20%에서 계속 실패하길래 왜 틀렸는지 문제에 다른 조건이 있는지
몇십분 생각했다.<br/>
분명 처음 문제 읽을때 그냥 기본적인 다익스트라라서... 최단경로 문제와 똑같이 짰음에도 계속 틀렸다고 뜨길래 답답했다.<br/>
결국 문제에서 다른 조건을 찾을 수 없어 그냥 다음날에 다시 봐야지 하고
다음날에 코드를 봤는데 어이없는 코드 오류를 찾을 수 있었다.