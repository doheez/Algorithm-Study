## Info

<a href="https://www.acmicpc.net/problem/17779" rel="nofollow">17779 게리맨더링 2</a>

## ❗ 풀이

주어진 배열을 1, 2, 3, 4, 5구역을 따로 구하는 함수를 만들었다.<br/>
5구역은 1, 2, 3, 4를 구하고 전체 인구에서 뺀 값으로 인구를 구했다.<br/>

인구 수를 구하는 알고리즘은 이중포문을 가지고 i와 j의 범위를 조정해서 구했다.<br/>
```java
for(int i = 2; i <N; i++) {
    for(int j = 1; j < i; j++) {
        d1 = j;
        d2 = i-d1;
        selectPosition(d1,d2);
        if(d1 == d2)
            continue;
        selectPosition(d2, d1);
    }
}
```
d1,d2는 `1 < d1 + d2 < N` 을 이용해서 이중포문을 만들어서 d1, d2가 되는 값을 구하고
```java
for(int x = 1; x < N-1; x++) {
    for(int y = 1; y <N-1; y++) {
        if(y-d1 < 0)
            continue;
        if(y+d2 > N)
            continue;
        if(x + (d1 + d2) > N )
            continue;

        calMin(x,y,d1,d2);
    }
}
```
x, y는 x, y가 되는 조건인 `y - d1 > 0`, `y + d2 < N`, ` x + d1 + d2 < N`을 이용해서 x, y 값을 구했다.<br/>


## ❗ 추가 지식


## 🙂 마무리

처음에 모든 구역의 인구 수를 한꺼번에 구하려고 해서 어떻게 if문을 구현할지 
머리가 터질뻔 했다.<br/>
이번 문제는 특별한 알고리즘은 없지만
어떻게 구현할지 생각한다고 시간이 엄청 걸렸다. 구현 문제연습을 많이 해야겠다.ㅠ<br/>