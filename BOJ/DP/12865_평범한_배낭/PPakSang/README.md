## Info

<a href="https://www.acmicpc.net/problem/12865" rel="nofollow">12865 평범한배낭</a>

## ❗ 풀이

배낭의 무게와 물건의 종류를 dp 표로 만들어서 해결할 수 있는 문제<br>
임의의 무게 K 일 때 물건 별로 넣을 수 있는지 여부를 체크하고 한 물건을 넣었다면 남은 공간에 넣을 수 있던<br>
최대 가치를 찾으면 된다.<br>

## ❗ 추가 지식

동적 계획법에서 bottom-up 방식을 채택한다면 배열은 0자리는 out of range 방어를 위해 비워두자<br>
top-down 방식을 채택한다면 dp 는 Integer 로 두고 null 체크를 하자


## 🙂 마무리
