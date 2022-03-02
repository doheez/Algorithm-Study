# 🔣 분할정복 (Divide And Conquer)

큰 문제를 풀 수 있는 작은 문제들의 합으로 해결하는 알고리즘으로, Dynamic Programming 과 간혹 헷갈릴 수 있다.<br><br>

**위키백과의 정의** 에 따르면 분할 정복 알고리즘(Divide and conquer algorithm)은 그대로 해결할 수 없는 문제를 작은 문제로 분할하여 문제를 해결하는 방법이나 알고리즘이다.<br><br>

구체적으로는 Dynamic Programming 은 부분해를 다음 단계에 이용하는 Memoization 기법을 사용하는데 반해<br>
Divide And Conquer 는 각각의 부분해가 다음 단계에 영향을 미치지 않는다.<br><br>

여기서 부분해란 피보나치 수열을 생각해보자면 P(0), P(1) ... P(N) 에서 N에 따른 값을 의마한다.<br>
다만 피보나치는 P(N) = P(N-1) + P(N-2) 의 이전 단계의 부분해를 활용하는 구조이므로 DP로 풀이하곤 한다.<br><br>

이와 유사하지만 Memoization 기법을 사용하지 않는 Divide And Conquer 방식을 생각해보자면 아래와 같은 예시가 있다.<br>
색종이를 계속해서 4등분 하는데, 특정 조건에 부합하는 색종이 가 존재하면  그 색종이를 버린다. N번 시행후에 몇개의 색종이가 존재하는가<br>
또는 Merge Sort 가 대표적인 예시이다.

