## Info

<a href="https://www.acmicpc.net/problem/1092" rel="nofollow">1092 배</a>

## ❗ 풀이

당일날 가장 많이(그리디하게) 일할 수 있는 방법은 모든 크레인에게 상자를 할당하는 것이다.  
상자를 할당하돼 무거운 상자부터 무거운 것을 들 수 있는 크레인에게 나눠줘야한다.

## ❗ 추가 지식

wrapper class 만 Comparator 사용 가능  
primitive type 은 실제 값이 stack 영역에 저장됨  
반대로 wrapper class 는 실제 값은 heap 에 저장되고 stack 영역에는 참조 주소가 저장됨

primitive type 메모리 사용량은 흔히 아는 type 별 데이터 크기  
reference type 은 포인터 개념이라 그런지 128 bits 사용함 (Long, Double 196bits)

Array -> Collection : Arrays.stream(ArrayObj).toList() 한 것을 Collection 생성자에 넣기  
new LinkedList<>(...)

## 🙂 마무리

