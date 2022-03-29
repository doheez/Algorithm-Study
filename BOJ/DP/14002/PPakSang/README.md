## Info

<a href="https://www.acmicpc.net/problem/14002" rel="nofollow">14002 가장 긴 증가하는 부분수열 4</a>
## ❗ 알고리즘

가장 긴 증가하는 부분수열 코드에서 함수로 표현된 부분을 for 문으로 바꿔서 작성해보았다.<br>
로직은 매우 흡사하다. dp[n]이 의미하는 것은 해당 숫자를 포함하는 부분수열 중 가장 긴 것의 길이이고,<br>
확인하고자 하는 인덱스에서 이전에 있던 수를 탐색하면서 만약 더 작은 수를 발견한다면 dp[k] + 1 이 최대인 index k 를 찾는 것이다.<br>
최종적으로 탐색을 마친 후 가장 최대가 되는 index의 기존 부분수열 + 현재수 를 붙인 것을 queue 로 저장한다.<br>

## ❗ 알고리즘 외

없음

## 🙂 정리

없음