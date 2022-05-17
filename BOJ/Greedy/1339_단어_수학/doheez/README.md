## Info

<a href="https://www.acmicpc.net/problem/1339" rel="nofollow">1339번: 단어 수학</a>

## ❗ 풀이
1. 알파벳과 각 단어를 구성하는 알파벳 자릿수 합을 Map에 저장한다.
2. Map을 자릿수 합을 기준으로 내림차순 정렬한다.
3. 자릿수 합이 큰 알파벳부터 큰 숫자를 부여해서 둘을 곱한다. 그리고 이 값들을 모두 더한다.

## ❗ 추가 지식
1. Comparator 객체를 사용하면 데이터를 좀 더 정교하게 정렬할 수 있다.
2. HashMap을 value 기준으로 정렬하고 싶을 땐, Map.Entry<>를 사용해서 List에 담은 후 List의 sort 메서드와 Comparator를 같이 사용해서 정렬한다.
3. Map의 모든 <key, value> 쌍을 출력하고 싶을 땐, 굳이 Iterator 객체를 쓰지 않더라도 Map.entrySet과 향상된 for문을 사용해서 간편하게 iterate할 수 있다. 혹은, 향상된 for문으로 Map.keySet()의 key를 하나씩 받아서 Map.get(key)로 value를 참조하며 값을 다룰 수도 있다.
4. Integer는 int와 다르게 `==` 대신 `equals()`로 값을 비교해야 한다. 전자를 써도 코드는 돌아가는데 시간이 좀 더 걸렸다.

## 🙂 마무리
처음엔 단순하게 길이가 긴 단어의 가장 큰 자릿값(알파벳)부터 큰 숫자를 부여하면 되겠다고 생각했는데, 그렇게 하니 수의 합이 최대가 되지 않는 경우가 생겼다.