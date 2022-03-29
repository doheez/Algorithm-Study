## Info

<a href="https://www.acmicpc.net/problem/14002" rel="nofollow">14002 가장 긴 증가하는 부분 수열 4</a>

## ❗ 풀이
<a href="https://github.com/doheez/Algorithm-Study/tree/main/BOJ/DP/11053/HyeW" rel="nofollow">11053 가장 긴 증가하는 부분 수열 풀이</a> <br/>
저번 주에 풀었던 가장 긴 증가하는 부분 수열의 문제에서 길이만 구하는게 아니라 수열까지 출력하는 것으로 메인 알고리즘은 같고 기능만 추가하면 되는 문제였다.<br/>
LIS 수열을 출력하기 위해서 아래와 같은 클래스를 이용한 배열을 만들었다. <br/>
```java
static class Node{
		int val;
		int pre;
		
		public Node(int val, int pre) {
			this.val = val;
			this.pre = pre;
		}
	}
```
Node[] arr = new Node[n]라고 사용하였을때 <br/> 
arr[i].val에는 arr[i]인자를 포함한 LIS의 길이를 저장하고, arr[i].pre에는 arr[i]를 포함한 LIS에서 arr[i]이전에 오는 index의 값을 저장했다.<br/>
그리고 마지막은 이 배열을 가지고 재귀로 출력을 하여 문제를 해결하였다.


## ❗ 추가 지식



## 🙂 마무리

LIS를 풀어본적 있어서 쉽게 풀었다.
