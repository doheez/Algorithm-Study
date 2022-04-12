## Info

<a href="https://www.acmicpc.net/problem/2668" rel="nofollow">2668 숫자고르기</a>

## ❗ 풀이

집합 표현을 visited 로 표현하였다.<br>
집합에 속하지 않은 숫자들에 대해서는 nodeSearch를 수행하는데,<br>
nodeSearch(i, next) 는 i로 시작했고 그 다음 수는 next 인 함수이다.<br>
i와 같은 수를 찾는 과정에서 i 가 아닌 이미 확인했던 수(visited) 와 만나면 i로 시작하는 dfs 수는 집합을 이루지 못한다.<br>
만약 다음 확인하는 수(next) 가 i와 같다면 i 는 집합을 이루는 첫 시작점이 될 수 있고(visited = true) 1을 return 한다.<br>
리턴값이 의미하는 바는 i 로 시작하는 dfs 가 집합을 이루었다는 뜻이다.<br><br>

만약 집합을 이루지 못했다면(리턴 값 0) 확인했던 수들을 다시 false 로 돌리고 메소드를 종료한다(return 0)

모두 확인한 후에 visited 에서 1로 표현된 수는 집합을 이루었다는 의미이므로 총 갯수를 구하고 오름차순으로 출력한다.

## ❗ 추가 지식

없음

## 🙂 마무리

없음
