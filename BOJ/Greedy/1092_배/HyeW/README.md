## Info

<a href="https://www.acmicpc.net/problem/1092" rel="nofollow">1092 배</a>

## ❗ 풀이

크레인(`c_list`)과 박스(`b_list`)를 ArrayList로 받아 내림차순으로 정렬한다.
```java
while (!b_list.isEmpty()) {
    int idx = 0;
    for (int i = 0; i < c;) {
        if (idx == b_list.size()) 
            break;
        else if(c_list.get(i) >= b_list.get(idx)) {
            b_list.remove(idx);
            i++;
        }else idx++;
    }
    ans++;
}
```

박스가 다 옮겨질때까지 while문을 반복한다.<br/>
idx는 박스 확인 횟수로, 옮겨진 박스는 remove되어 list size자체가 줄어 들어 idx++할 필요가 없고
옮겨지지 못한 박스만을 idx++해주면 된다.<br/>

idx가 박스 리스트 사이즈와 같아지면 옮길 수 있는 박스는 다 선택된 것이므로
크레인을 확인하는 for문을 벗어나 시간(ans)을 증가시키면 된다.<br/>

## ❗ 추가 지식

arr.size() > 0 보다 arr.isEmpty()을 사용해서 가독성을 높이자.

## 🙂 마무리

계속 시간 초과가 났던 문제이다. 처음에는 박스를 옮길 수 있는지 모든 박스를 확인 했다면
크레인을 더 보지 않고 1분을 증가시키면 되는데 그 조건이 없어 시간 초과가 났다.<br/>
1,000,000개를 최대 10,000번 읽어야 하니 시간 초과가 난 것이다. 크레인을 다 보는 것이 아닌
박스를 다 보는 것에 초점을 맞춰야 했다!<br/>

하지만 이 처리를 해주고도 계속 시간 초과가 나서 알아본다고 이것 저것 수정했는데 알고보니 
박스를 옮길 수 없을때 -1해주고 return을 해야 하는데 코드 수정하면서 삭제가 실수로 되어 헤맸던 것이었다.<br/> 


