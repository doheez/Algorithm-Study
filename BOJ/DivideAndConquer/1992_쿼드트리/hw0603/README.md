## Info

<a href="https://www.acmicpc.net/problem/1992" rel="nofollow">1992 쿼드트리</a>

## ❗ 풀이

`isAllSame(list)` 함수에서 2D List의 원소가 모두 같은지 여부를 판단한다.  
`solution(list)` 의 인자로 들어온 리스트를 대상으로 원소가 같은지 판별한 후, 같다면 첫 번째 원소의 데이터로 압축한다.  
리스트에 서로 다른 원소가 존재한다면 해당 리스트를 4등분하고, 분할된 각 리스트를 대상으로 `solution()`을 재귀호출하여 모든 원소가 같아질 때(=압축이 가능해질 때) 까지 분할한다.

## ❗ 추가 지식

리스트와 리스트를 concatenate 할 때, 추가되는 리스트의 원소가 하나라면 `list.append()`가, 리스트의 원소가 다수라면 `listA += listB`가 더 빠르다.  
`list.append()`는 `LOAD_ATTR` + `CALL_FUNCTION`을 사용하고, `+=`(iadd 연산자)는 `BUILD_LIST`를 사용하므로 `append()`함수가 성능이 좋을 수 밖에 없는 것.  
  
결론: 리스트에 원소 하나 추가 시 `append()`사용, 리스트에 리스트 연결 시 `extend()` 혹은 `+=` 사용.

## 🙂 마무리

vscode PyLance에서 코드 입력 시 Auto-Import 기능이 켜져 있어서 사용하지 않을 모듈들이 가끔 import 되어 있는 경우가 있다. 백준 제출 전에 확인 한 번 하고 제출하기.  
자주 쓰는 계산 값들은 계속 계산하지 말고 변수에 담아서 쓰는 습관 들이기
