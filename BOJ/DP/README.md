# 🔣 동적 계획법 (Dynamic Programming)
<br/>

_DP는 '하나의 문제는 단 한 번만 풀도록 하는 알고리즘'이다_
<br/>
#### 1. 분할정복 vs 동적 계획법<br/>
DP는 분할 정복의 동일한 문제를 다시 푸는 문제점을 보완한 기법이라고 생각해도 좋다
<br/><br/>
피보나치 수열을 예로 들어 확인해보자<br/>
```c
int fibo(int x){
  if(x == 1) return 1;
  if(x == 2) return 1;
  return d(x - 1) + d(x - 2);
}
```
위 경우 아래의 그림과 같이 fibo(15)를 구할 경우 fibo(13), fibo(12), fibo(11)등을 중복해서 구하게 된다<br/>
이미 해결한 문제를 다시 반복적으로 구하는 것은 매우 비효율적이다<br/>
![image](https://user-images.githubusercontent.com/57346443/157718315-25156da9-a2f2-4526-9d60-351f7dcd31dc.png)
<br/>
따라서 이런 경우에는 DP 기법을 사용해야 한다
<br/><br/>
DP의 핵심은 `'메모이제이션(Memoization)'`을 사용하는 것으로 분할 정복의 문제점을 해결한다<br/>
메모이제이션은 이미 계산한 결과는 배열에 저장하여 나중에 동일한 계산을 해야 할 때 배열에 저장된 값을 단순히 반환하기만 하면 되는 것이다<br/>
피보나치 수열을 DP기법을 사용하여 풀면 아래와 같이 작성할 수 있다<br/>
```c
int arr[100];

int fibo(int x){
  if(x == 1) return 1;
  if(x == 2) return 1;
  if(arr[x] != 0) return arr[x];
  return arr[x] = fibo(x - 1) + fibo(x - 2);
}
```
위와 같이 코드를 작성하면 이미 계산된 결과는 배열에 저장되기 때문에 한 번 구한 값을 다시 구하는 일은 존재하지 않게 된다<br/><br/>
#### 2. 동적 계획법 구현방식<br/>
DP를 구현하는 2가지 방식이 있는데 TOP-DOWN과 BOTTOM-UP이다<br/><br/>
`TOP-DOWN`은 내가 구하고자 하는 큰 문제에서 시작하여 점점 작은 문제로 쪼개어 해결하는 방법이다<br/>
재귀함수를 이용하여 TOP-DOWN을 구현할 수 있다<br/>
아래의 코드가 TOP-DOWN방식이다<br/>
```c
int arr[100];

int fibo(int x)
{
  if (x<=2) 
    return 1;
  if (arr[x]==0)
    arr[x] = fibo(x-1) + fibo(x-2);
  return arr[x];
}
```
<br/>

`BOTTOM-UP`은 아래에서 위로 풀이가 진행되는 방식으로 작은 문제부터 차근차근 구해나가는 방법이다<br/>
for문을 이용해 BOTTOM-UP을 구현할 수 있다<br/>
아래의 코드가 BOTTOM-UP 방식이다<br/><br/>

```c
int arr[100];

int fibo(int x){
  arr[0] = 0;
  arr[1] = 1;
  for (int i=2; i<=x; i++)
    arr[i] = arr[i - 1] + arr[i - 2];
  return arr[x];
}
```
<br/>
TOP-DOWN방식은 점화식을 이해하기 쉽다는 장점이 있고, BOTTOM-UP방식은 함수를 재귀호출하지 않아 시간과 메모리 사용량을 줄일 수 있다는 장점이 있다 <br/>
문제를 풀다 보면 두 방식의 구현 난이도가 심하게 차이 나는 상황을 만나기 때문에 두 방식을 모두 사용할 수 있도록 연습해야 한다<br/>
<br/><br/>
참고 사이트<br/>

<https://blog.naver.com/ndb796/221233570962> <br/>

<https://velog.io/@chelsea/1-%EB%8F%99%EC%A0%81-%EA%B3%84%ED%9A%8D%EB%B2%95Dynamic-Programming-DP> <br/>

<https://semaph.tistory.com/16> <br/>
