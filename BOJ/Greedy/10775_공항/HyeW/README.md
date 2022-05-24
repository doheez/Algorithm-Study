## Info
<a href="https://www.acmicpc.net/problem/10775" rel="nofollow">10775 공항</a>

## ❗ 풀이

Union & Find를 사용하는 문제이다.
K비행기가 들어오면 K번 게이트의 부모를 확인한다. **(find)** <br/>
부모가 0이 아니라면 1 ~ K사이 도킹할 수 있는 게이트가 있다는 것으로
비행기를 도킹시킬 수 있다.<br/>
그리고 해당 부모값을 root라 했을때, 이제 root에는 도킹을 했으니 root 앞 게이트에
남는 자리와 연결해주기 위해 root-1번 부모와 합친다. **(union)** <br/>
부모가 0이라면 도킹할 수 있는 자리가 없는 것이므로 답을 출력하고 끝내면 된다.

## ❗ 추가 지식
Union Find 알고리즘
```java
public static int find(int now) {
  if(now == parent[now])
      return now;
  return parent[now] = find(parent[now]);
}

public static void union(int x, int y) {
  x = find(x);
  y = find(y);
  
  if(x!=y) {
      parent[x] = y;
  }
}
```

## 🙂 마무리

처음엔 비행기가 들어올때마다 자신이 도킹할 수 있는 비행장을 다 확인해보는 브루트포스 방법으로 짰는데
검색을 하니 UnionFind로 문제를 해결하는 것이었다.<br/>
이 문제 덕분에 1년만에 UnionFind를 써봤다.  
