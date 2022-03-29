## Info

<a href="https://www.acmicpc.net/problem/12865" rel="nofollow">12865 평범한 배낭</a>

## ❗ 풀이

dp 이차원 배열을 만들어서  row는 n번째 아이템, col은 가방이 허용하는 무게로 두고 문제를 해결했다.<br/>

```java
 for (int i = 1; i <= n; i++) {
     int temp_w = it[i].w;
     int temp_v = it[i].v;
     for (int j = 1; j <= w; j++) {

        if ((j - temp_w) >= 0){
        bag[i][j]=Math.max(bag[i-1][j-temp_w]+temp_v,bag[i-1][j]);
        } else {
             bag[i][j] = bag[i-1][j];
        }
        max = Math.max(max, bag[i][j]);
     }
}
```
i번째 물건을 넣을려면 j(가방이 담을 수 있는 무게)를 확인해야 한다.<br/>
<br/>
i번째 물건의 무게보다 가방의 무게가 크다면 i번째 물건을 담을 수 있다.<br/>
하지만 i번째 물건을 넣은 값(bag[i-1][j-w] + v : i번째를 넣기 전 i번째가 들어갈 무게를 뺏을때 가장 큰 값)과<br/> 
i번째 물건을 넣지 않았을 때 가장 큰 값(bag[i-1][j])를 비교하여 bag[i][j]에 더 큰 값을 넣는다.<br/>
<br/>
i번째 물건의 무게 보다 가방의 무게가 작다면 i번째 물건은 담을 수 없으니<br/>
i-1번째까지 담았을 때의 가장 큰 값을 넣어준다. 

그러면 bag[i][j]에는 i번째 물건까지 있을때, j무게를 담을 수 있는 가방에 물건가치의 최대값을 저장하고 있는것이다.<br/>


## ❗ 추가 지식



## 🙂 마무리

사실 클래스 하나를 더 안 만들어도 되지만 짝이 있는 input값이 보일 때 마다 클래스를 만드는 버릇이 생겨버렸다.<br/>
이 버릇 고쳐 쓸데 없는 코드를 빼야한다.<br/>
