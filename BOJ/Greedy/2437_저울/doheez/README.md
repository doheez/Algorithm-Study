## Info
<a href="https://www.acmicpc.net/problem/2437" rel="nofollow">2437번: 저울</a>

## ❗ 풀이
먼저 저울추를 적은 무게부터 오름차순으로 정렬한 뒤 하나씩 올려보며 가장 적은 무게부터 차례대로 무게를 확인한다.
만약 현재 올리려는 저울추의 무게가 지금까지 올린 저울추의 총 합 + 1 보다 커지는 순간 저울추의 총 합 + 1이 측정할 수 없는 최소값이 된다.

## ❗ 추가 지식

## 🙂 마무리
우선 입력받은 추를 오름차순으로 정렬하고, 만들 수 있는 무게를 1부터 시작해서 1씩 늘려가야겠다는 것까진 생각했는데 어떻게 최솟값을 구할지 감이 안 와서 답을 봤다.
참고한 블로그: https://plplim.tistory.com/59