## Info

<a href="https://www.acmicpc.net/problem/13023" rel="nofollow">13023 ABCDE</a>

## ❗ 풀이

문제는 n명의 사람들 중 5명의 사람이 연쇄적으로 친구를 맺고 있다면 1을 출력하고 아니면 0을 출력하는 문제이다.<br/>
먼저 인접리스트로 input값을 받아 저장한다.<br/>
dfs함수를 사용하여 dfs의 깊이가 5까지 간다면 바로 함수를 종료하고 리턴 값으로 1을 전달하여 main에서 1을 출력하고 종료하고<br/>
깊이가 5까지 가지 못한다면 dfs함수에서 0을 리턴하여 그 리턴 값을 출력한다.<br/>

## ❗ 추가 지식

## 🙂 마무리

이번 문제는 질문 이해부터하기가 어려웠다. 
혼자 이해하려면 오래 걸렸을텐데 도희의 도움으로 문제를 이해하고 풀 수 있었다.<br/>
<br/>dfs에서 리턴값을 쓰는 건 여전히 어렵다.
하지만 이번엔 dfs내용이 간단하여 다른 도움없이 리턴값을 활용하여 코드를 구현하였다.
실력이 늘고 있다는 느낌이 든다. 이제 dfs 중급사용자 정도 되진 않을까..?