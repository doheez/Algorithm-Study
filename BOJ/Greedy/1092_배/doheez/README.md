## Info
<a href="https://www.acmicpc.net/problem/1092" rel="nofollow">1092번: 배</a>

## ❗ 풀이
1. 크레인 무게 제한 리스트와 박스 리스트를 내림차순으로 정렬한다. 
2. 박스가 크레인 제한 무게보다 가볍거나 같다면 박스 리스트에서 제거하며 다음 크레인으로 넘어가고, 무겁다면 다음으로 큰 박스를 검사한다.
3. 크레인 수만큼 2번을 진행한 후 시간을 증가시킨다.
4. 2~3번을 박스 리스트가 빌 때까지 반복한다.

## ❗ 추가 지식
### 코딩테스트를 위한 Java 팁
1. 자바 코드를 백준에 제출할 때 클래스명은 `Main`, 패키지는 없어야 한다.
2. `main` 함수에서 쓸 변수를 `main` 밖에서 선언하려면 `static`을 붙여야 한다. `main`이 `static` 함수이기 때문이다. `main` 밖 변수들에 static`을 붙이고 싶지 않다면 함수로 한 번 래핑하면 된다.
3. 입력: `Scanner`와 `split`은 느리므로 `BufferedReader`, `StringTokenizer`를 사용한다.
4. 출력: `println`은 느리므로 `BufferedWriter` 사용 또는 출력할 문자열들을 `StringBuilder`로 묶어서 `println`을 한 번만 사용한다.
5. `BufferedReader`, `BufferedWriter`는 사용 후 `close()`해줘야 하고, 출력할 문자열이 버퍼 크기보다 작은 경우 직접 `flush()`해줘야 출력된다.

### Sorting
일반 배열을 오름차순 정렬할 땐 `Arrays.sort()`를 사용하며, 내림차순은 인자로 `Collections.reverseOrder()`를 넣어준다. 단, Wrapper Class만 가능하므로 int형 배열의 경우 Integer 배열로 선언해야 한다.

ArrayList를 정렬할 땐 List 객체의 `sort` 메서드를 사용할 수 있으므로 ArrayList `변수명.sort()` 형태로 사용할 수 있다.

## 🙂 마무리
자바 적응 파이팅...