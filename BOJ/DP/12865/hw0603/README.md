## Info

<a href="https://www.acmicpc.net/problem/12865" rel="nofollow">12865 평범한 배낭</a>

## ❗ 풀이
`dp[N+1][K+1]` 크기의 2차원 배열을 선언하고, `dp[i][j] = i번째 물건까지 조사했을 때, 무게 j로 만들 수 있는 최대 가치 (i, j >0)`로 한다.  
n번째 시행에서, 

1. 현재 물건을 넣는 경우
   - `dp[i][j] = dp[i-1][j-현재 물건의 무게] + 현재 물건의 가치`
   - 지금까지 조사한 경우들 중 현재 물건의 무게를 뺐을 때의 최대 가치 + 현재 물건의 가치
2. 현재 물건을 넣지 않는 경우
   - `dp[i][j] = dp[i-1][j]`
   - 한 단계 이전 시행의 최선의 경우와 동일  

이렇게 두 가지 경우가 존재할 것이다. 기본적으로는 두 가지 경우 중 더 가치가 높은 경우를 택하여 저장하면 된다.  
만약 현재 물건이 너무 무거워서 현재 물건 하나로도 무게 제한을 넘어 버리는 경우에는 배낭에 그 물건을 넣을 수 있는 경우가 없으므로 항상 현재 물건을 넣지 않는 경우로 처리하면 된다.  
  
모든 시행을 마치고 나면 `dp[-1][-1]`에 무게제한이 K일 때, N번째 물건까지 조사했을 경우 가치의 최댓값이 담겨 있을 것이다.

## ❗ 추가 지식
`a, b = map(int, input().split())` 이런 형태로 한 줄에 여러 데이터가 있는 문자열을 받고 각각 변수에 넣을 때, `map` 객체를 `list(map())`과 같이 변환해 줄 필요가 없다. `a, b` 자체가 언패킹의 의미를 가져서 map객체가 알아서 풀어진다.

## 🙂 마무리
3, 4일을 한 문제만 잡고 고민하다가 도저히 안되겠어서 (<a href="https://hongcoding.tistory.com/50">풀이</a>)를 참고했다.  
기존까지 풀던 문제와 다른 점은, 이 문제에서는 "배낭의 무게" 라는 제한 조건이 하나 더 추가된 것.  
이러한 유형의 문제를 "Knapsack 알고리즘" 이라 한다고 한다. 
  
처음에는 dp배열을 1차원 배열로 선언한 후, `dp[i] = arr[i] 번째 물건을 마지막으로 포함하는 가장 최선의 경우`로 잡고, 마지막에 `dp[i]의 최댓값`을 구하면 정답이 될 것으로 생각하고 풀었었다.(이전 커밋 참고)  

```
6 10
6 1
1 1
4 8
3 6
5 12
1 1
```
하지만 입력이 다음과 같은 경우, 가치의 최댓값은 (4, 8)->(5, 12)->(1, 1) 으로 21이 나와야 하는데 12가 출력되어 살펴 봤더니, (지금까지의 경우 + 현재 물건) 을 했을 때 무게 제한을 넘지 않는 경우가 없다면 최선의 경우가 구해지지 않아서 이 방식으로는 풀 수 없었다.  
  
다시 말해, (0...n-1)번째 시행에서의 최선의 경우에서 n번째 물건의 무게를 더하는 경우들 중 가치의 최댓값이 나온다고 해서, 그 경우가 n번째 시행에서의 최선의 경우라고 장담할 수 없는 문제가 있었다. 그렇다면, 그 경우의 최선의 경우를 다시 구해야 할 텐데, 이는 이전 시행의 결과를 활용하지 못하므로 dp의 장점을 제대로 사용하지 않는 것이였고 이전 시행들의 과정을 모두 어떻게든 저장을 해 두어야 하나.. 라고 고민하다가 도저히 생각이 안 나서 풀이를 봤다.
  
풀이에서는 2차원 배열을 사용하여 각 시행에서 무게 제한 별로 만들 수 있는 최대의 가치들을 모두 저장하고 있었고, 각 시행에서 물건을 넣는 경우와 넣지 않는 경우 중 항상 가치가 높은 것을 택하는 방법을 취했다. 처음 내 풀이와 다르게 무게와 가치 두 가지 변수를 모두 고려하며 헷갈릴 일도 없었고, 명쾌하고 간단했다. 처음 풀이법대로 어떻게든 풀어 보려고 며칠을 붙잡고 있었는데 접근부터 잘못됐었다는 사실을 알고 맥이 좀 빠지긴 했지만, 2차원 배열을 사용하려는 생각은 한 번도 한 적이 없었기에 좀 더 많은 문제를 풀어 보며 경험을 쌓아야겠다 싶었다.
  
### 추가적으로 드는 의문:
- 변수가 하나 추가(무게제한)되었을 경우에 1차원 배열로 풀지 않고 2차원 배열을 활용해서 풀었다. 그렇다면 변수가 3개(포션을 먹는데 무게, 독성, 효과 모두를 고려하는 경우 등)가 된다면 3차원 배열로 DP를 구현해야 하는가?
- 처음 내 방식대로 접근할 수 없는 이유가 정확히 무엇인가?
  - 단순히 무게제한 때문에 (이전 최적해 + 현재 물건)이 현재의 최적해임을 보장할 수가 없어서?
  - 그렇다면 무게 제한이 없다고 생각하고 풀이한 다음 DP 배열 중에 무게제한에 맞는 최댓값을 구하면 안 되는 이유는?