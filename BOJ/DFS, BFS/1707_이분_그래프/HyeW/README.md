## Info
<a href="https://www.acmicpc.net/problem/1707" rel="nofollow">1707 이분 그래프</a>

## ❗ 풀이
이분 그래프는 정점들을 두 집합으로 나눌 수 있는데 같은 집합에 속한 정점끼리는 간선이 존재하지 않아야 한다.<br/>
그래서 bfs를 사용하여 내가 1집합에 속해 있다면 나와 연결된 집합들은 2집합이 되도록 코드를 작성하였다.<br/>
<br/>
나는 1, -1로 집합을 나누었다.<br/>
나와 연결된 정점이 집합을 가지고 있지않으면 집합 수에 x(-1)을 해주었고,<br/>
집합이 정해져있는데 내 집합 수 x(-1)이 아니라면 이분 그래프가 아니므로 "NO"를 출력한다.<br/>
<br/>
그리고 여러 그래프가 나올 수 있기때문에 전체 정점들을 확인하면서 visited가 되지않은 정점을 모두
이분 그래프 체크를 하였고 하나라도 이분 그래프가 되지 않으면 "NO"를 출력하도록 하였다.<br/>

## ❗ 추가 지식
없음

## 🙂 마무리
이분 그래프 정의만 안다면 알고리즘은 생각하기 쉬운데, 여러 예외 케이스들을 생각해야 해서 어려운 문제였다.<br/>
계속 틀리길래 정점이 다 연결 안될 수도 있겠다라는 생각까진 갔는데 그 정점이 하나일 거라는 생각에 사로 잡혀 결국 블로그를 보고
한 케이스에서 여러 그래프가 나타날 수 있다는 사실을 깨달았다.<br/>
난 사고가 꽉 막힌 사람....<br/>