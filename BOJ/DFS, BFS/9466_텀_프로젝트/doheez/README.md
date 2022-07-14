## Info

<a href="https://www.acmicpc.net/problem/9466" rel="nofollow">9466번: 텀 프로젝트</a>

## ❗ 풀이
dfs로 사이클이 있는지 검사하는 문제이다. visited 배열 외에도 done이라는 배열을 두어서, dfs 함수 마지막에 현재 노드에 대한 done 값을 true로 바꿔준다. 이렇게 해서 더이상 해당 노드를 방문할 일이 없다는 것을 체크한다.

 그리고 dfs를 할 때 다음 노드를 방문은 했지만 (visited 값 true) 방문이 완전히 끝나지 않았을 때 (done 값 false) 사이클이라고 판단하고 사이클을 이루는 학생들의 인원을 카운팅한다.

dfs 후엔 전체 학생 수에서 사이클을 이루는 학생 수를 뺀 값을 출력한다.

## ❗ 추가 지식


## 🙂 마무리
참고한 블로그: https://jaimemin.tistory.com/674