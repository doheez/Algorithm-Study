## Info
<a href="https://www.acmicpc.net/problem/10775" rel="nofollow">10775 공항</a>

## ❗ 풀이

비행기가 격납고로 들어오는데, 일정 범위 내에 있는 격납고만 들어갈 수 있다.

격납고의 범위가 큰 비행기는 가장 우측에서 부터 들어가는게 맞고, 격납고에 비행기가 있으면 한 칸 왼쪽의 격납고가 비었는지 보면 된다

근데 한 칸 왼쪽을 봤는데 또 차 있으면 또 왼쪽을 봐야하고 반복을 해서 시간초과가 날 위험이 있다.

규칙을 살펴보면 해당 격납고에 들어온 비행기가 몇 대 인지는 체크 가능할거고 그러면 적어도 n개 들어왔으면 n-1 칸 옆에 비행기를 넣어야한다.

똑같이 n-1 칸 좌측에 격납고를 살펴보니 3칸 좌측을 보라고 한다면 위 n-1 칸에 n-1 + 3 으로 업데이트 해 준다면 다음 번에 또 탐색 범위를

절약할 수 있을 것이다. 이러한 테크닉을 사용하는 것이 union - finder 이다

## ❗ 추가 지식

핵심은 서로소가 되는 집합을 만드는 것이 핵심이고, 본 문제에서는 대표값이 되는 인덱스를 제외하고 가득 차 있는 격납고를 표현하기 위해

사용하였다. 본 문제에서 의미는 find(idx) -> 해당 격납고(집합 대표값) 가 비었다. 를 나타내고

union(a-1, a) 는 a 격납고에 비행기를 채우고, 바로 좌측 격납고 집합과 합치라는 의미가 된다.

## 🙂 마무리

없음
