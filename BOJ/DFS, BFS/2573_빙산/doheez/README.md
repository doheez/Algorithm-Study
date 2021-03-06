## Info

<a href="https://www.acmicpc.net/problem/2573" rel="nofollow">2573번: 빙산</a>

## ❗ 풀이
1. 0의 개수를 세어 빙산이 모두 녹았는지 검사한다. 빙산이 모두 녹았는데도 분리되지 않았다면 0을 출력하고 종료한다.
2. 빙산 배열을 전체 탐색하며 빙산을 녹인다. 빙산을 녹일 때엔 `icebergTest` 배열을 사용하고, 특정 원소의 상하좌우가 바닷물인지 확인할 때엔 `icebergOrig` 배열을 사용한다. 빙산을 녹인 후엔 `icebergTest`를 `icebergOrig`로 복사한다. 빙산을 녹일 때 외에 빙산 배열을 참조할 때는 `icebergOrig` 배열만을 사용한다.
3. 시간을 증가시킨다.
4. 빙산의 모든 원소에 대해 dfs 탐색하고 발견하는 빙산 개수를 카운팅하여 2개 이상 발견했다면 시간을 출력하고 종료한다.
5. `visited` 배열을 초기화한다.
6. 빙산이 갈라지거나 모두 녹을 때까지 1~5번을 반복한다.

## ❗ 추가 지식


## 🙂 마무리
내가 한 실수
1. dfs에서 다른 빙산 원소로 이동하는 과정을 '현재 빙산 원소가 바닷물일 때'에도 진행해서 스택 오버플로우가 일어났다.
2. 빙산 개수를 카운팅하는 변수를 while문 밖에서 선언하고서 반복문이 진행될 때마다 초기화를 해주지 않았더니 값이 누적되어 오답이 나왔다.