## Info
<a href="https://www.acmicpc.net/problem/1781" rel="nofollow">1781 컵라면</a>

## ❗ 풀이

데드라인 순으로 정렬을 하고 minheap에 정렬 순으로 컵라면 갯수를 넣는다.<br/>
```java
for(Problem cur : problem) {
    int day = solve.size();
    if(day < cur.day) {
        solve.add(cur.cup);
    }else if(day == cur.day) {
        if(solve.peek() < cur.cup) {
            solve.poll();
            solve.add(cur.cup);
        }
    }
}
```
이때 우선순위 큐 사이즈가 문제를 푼 개수가 되는데 만약 새로운 문제의 데드라인이 푼 문제 개수보다 크다면 그냥 큐에 넣으면 된다.<br/>
데드라인이 같을 때 heap을 쓰는 이유가 나타난다.<br/>
푼 문제 중 가장 작은 컵라면 개수를 새로운 문제의 컵라면 개수와 비교하여 푼 문제의 컵라면 개수가 더 적으면
새로운 문제를 푸는 것이 더 이득이기 때문에
해당 문제를 빼고 새로운 문제를 큐에 넣어준다.
## ❗ 추가 지식

Arrays sort에서 `Arrays.sort()`와 `Arrays.parallelSort()`가 있다.<br/>
병렬 소팅은 스레드를 사용하는데 **적은 데이터에선 sort()** 를 사용하는 것이 맞고,
**대량의 데이터 처리에서 parallelSort()** 를 쓰는게 맞다.<br/><br/>
<a href="https://choidev-1.tistory.com/33" rel="nofollow">참고 사이트1</a>
<br/><a href="https://stackoverflow.com/questions/17328077/difference-between-arrays-sort-and-arrays-parallelsort" rel="nofollow">참고 사이트2</a>
<br/><br/>
또한 Queue에서 유사한 메소드들이 있다.<br/>
추가 : add(), offer() <br/>
삭제 : remove(), poll() <br/>
검사 : element(), peek() <br/>
메서들끼리 기능적 차이는 없지만 예외 상황이 발생했을때 예외를 던지는지 null또는 false를 반환하는지가 다르다.<br/>
추가에서 add()는 예외를 발생시키지만 offer()은 추가 실패를 의미하는 false를 리턴한다.<br/><br/>
<a href="https://goodteacher.tistory.com/112" rel="nofollow">참고 사이트1</a>
## 🙂 마무리

테케가 어떤 순으로 정렬하든 답이 잘 나와서 내가 테케를 잘 생각하고 코드를 짜야했다.<br/>
처음에 짤 때는 [{1,10},{1,20},{2,100},{2,100}] 같은 경우를 생각 못하고 데드라인 순으로 
정렬을 해서 담았었다. 그 다음 컵라면 순으로 정렬을 하다가 우선순위 큐를 써야한다는 것을 
인터넷 찾아보고 깨달았다.<br/>
그리디..문제는 간단해보이는데 너무 어려워<br/>