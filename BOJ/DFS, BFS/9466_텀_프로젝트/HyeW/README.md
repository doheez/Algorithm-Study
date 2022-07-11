## Info
<a href="https://www.acmicpc.net/problem/9466" rel="nofollow">9466 텀 프로젝트</a>

## ❗ 풀이
여기서 visited는 사이클을 이루는지 아닌지 확인하는 배열이고 finished는 사이클 계산이 끝났음을 의미하는 배열이다.<br/>
dfs를 통해 연결된 학생을 확인했을 때 visited가 true이면 사이클이 이루어졌음을 의미한다.<br/>
```java
if (!visited[next])
        dfs(next);
    else {
        if (!finished[next]) {
            cnt++;
            for (int i = next; i != x; i = root[i])
                cnt++;
        }
    }

finished[x] = true;
```
위와 같이 다음 학생(next)의 visited가 true이고 finished가 false이면 사이클을 이루는 학생을 수를 세아리고 
현재 학생(x)의 finished를 true로 설정하고 마친다.<br/>
예를 들어 2 -> 1 -> 3 -> 3 경우가 있다. <br/>
3을 확일할 때 visited가 true임을 보고 finished를 확인한 후 사이클을 이루는 학생을 수를 계산한다.<br/>
finished[3] = true가 되고, 차례로 1, 2도 finished가 true처리된다.<br/>
다음에 4 -> 2와 같이 dfs를 시행할 때 visited가 true여도 finished가 true로 사이클이 처리됐음을 의미하여
**finished가 true로 처리된 학생들은 더이상 확인할 필요가 없다.**<br/>

## ❗ 추가 지식
없음
## 🙂 마무리
<a href="https://www.acmicpc.net/problem/13023" rel="nofollow">13023 ABCDE</a>와 비슷한 문제로 인식하고 접근했는데 
시간초과가 났다. 블로그를 찾아보니 하나하나 모든 인자에 대해 dfs하는 것은 O(n^2)이라고 했다.<br/>
그래도 사이클이 만들어지면 사이클을 이루는 값들을 visited처리 해주기때문에 시간이 단축될 것이라고 생각했는데 아니었나보다.<br/>
그래서 블로그에서 finished배열에 대한 설명만 보고 코드를 짜려고 하니 머리가 잘 굴러가지 않아서 혼자 결국 코드 전체를 보고 문제를 해결했다.<br/>
dfs문제를 많이 풀어봐야겠다...<br/>