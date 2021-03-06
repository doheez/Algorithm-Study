## Info
<a href="https://www.acmicpc.net/problem/2437" rel="nofollow">2437 컵라면</a>

## ❗ 풀이

그리디 문제는 정렬부터 하는게 해결책을 찾는 시작점인 것 같다. 물론 아주 희박한 확률로 그렇지 않은 문제도 존재할 수 있겠지만  
최적 부분 구조를 형성하기 위해서는 linear 한 형태로 문제 상황을 해결해 나가는게 우선이라고 판단했기 때문이다.

이번 문제에서도 추가 형성할 수 있는 무게를 파악할 때, 추가 만들 수 있는 가장 작은 무게부터 구하는게 핵심이라고 생각했다.
1. 처음에는 추가 만들 수 있는 무게를 모두 구하면서 현재까지 나온 만들 수 있는 추의 무게 + 1 이 나오지 않는다면 만들 수 없는 지점  
   이라고 판단하고 출력을 하려 했지만, 일단 실패 그리고 속도도 너무 느려보였다

2. 이전에 만들 수 있는 추의 무게와 그 다음 무게의 추가 들어왔을 때 변화를 살펴보니 한가지 특징을 발견했다  
   현재까지 만들 수 있는 추의 무게는 연속(+1씩 증가) 하기 때문에 (새로 들어온 수 + 1 ~ 현재 최대 무게 + 새로 들어온 수) 까지는 무조건  
   만들 수 있는 것이다. 따라서 해당 범위가 이전 범위와 비교했을 때 끊어지지만 않는다면 현재 최대 무게 + 새로 들어온 수 까지는 만들 수 있다

좀 더 정리 해서 (현재 만들 수 있는 무게 + 1) 이 "새로 들어온 추" 보다 크거나 같기만 하다면 "만들 수 있는 무게"가 연속하고  
현재 만들 수 있는 무게를 업데이트 해주면 된다.  
도 중에 끊기면 출력, 안 끊기게 만들면 만들 수 있는 최대 무게 + 1 출력


## ❗ 추가 지식

없음

## 🙂 마무리

없음
