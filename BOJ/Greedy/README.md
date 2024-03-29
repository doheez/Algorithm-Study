# 🔣 탐욕법  (Greedy)
<br/>

탐욕스럽고 욕심많은 전략을 택한다고 해서 붙여진 Greedy Algorithm 은
현재 문제 상황에서 가장 큰 이득을 가져다 줄 것으로 예상이 되는 선택을 하는 방식으로 문제를 해결해 나간다.

단, 여기서 조건이 하나 더 붙는데, 어떤 선택을 했을 때 문제의 최적 부분 구조를 만들 수 있는 것 만이 Greedy 하다고 할 수 있다.

### 최적 부분 구조 (Optimal SubStructure)

최적 하부 구조를 만족하기 위해서는 순간의 최선의 선택이 문제 해결 전체를 놓고 보았을 때도 최선의 선택이 되어야 한다.  
외부에서 발췌한 문장에 따르면 "지역적 최선 해가 전역적 최선 해를 만족하는 것" 이라고 한다.  
  

### 현재의 선택이 미래에 영향을 주지 않는다

Greedy 는 앞서 배운 Dynamic Programming 과 비교하여 문제를 해결하는 최적의 선택을 한다는 점에서 유사한 점이 많은데,   
한 가지 차이점을 뽑자면 그리디 방식은 이전의 선택이 이후의 선택에 영향을 미치지 않는다.  
가령, `Tn+1 = 100*Tn + 20*Tn-1` 과 같은 DP 해를 구하는 문제는 식에서도 알 수 있듯 이전의 해가 현재의 해에  
영향을 미친다는 것을 알 수 있다.  
즉 위 식의 현재의 선택(n+1) 이 과거의 선택에 상당히 의존적이라는 뜻이다.

### 근사치 추정

위의 두가지 조건을 만족하지 못한다고 해서 그리디 알고리즘을 사용할 수 없는 것은 아니다.  
그리디 알고리즘은 **근사치 추정** 에 매우 용이한데, 해를 구하는 방식 자체가 현재 선택할 수 있는 해 중  
가장 많은 부분 문제를 해결할 수 있는 선택을 하기 때문에 모든 문제를 해결하지 못한다고 하여도, 어느정도 문제를  
해결하고 어느정도 보장된 답(근사치) 을 도출하리라 예상할 수 있다.

### 활용 문제

그리디와 DP 의 차이는 Knapsack Problem 에서 극명하게 드러난다.  
기본적으로 Knapsack 문제는 현재 가방의 무게 w 에서 k 번 째 아이템을 넣었을 때  
DP[w,k] = max(DP[w - wk, k-1] + vk, DP[w, k-1]) 의 이전에 구한 해를 사용한다.  
    
반대로 이 문제를 그리디로 해결하려고 시도한다면, 현재 가방에 넣을 수 있는 최고의 무게 혹은 비슷한 그리디 전략은  
위에 점화식에서 확인할 수 있듯이 항상 최적해를 보장하지 않는다는 것을 알 수 있다  
  
하지만 Knapsack 문제를 Fractional Knapsack 으로 옮긴다면 그리디 알고리즘이 적용 가능한데,  
그 이유는 Fractional Knapsack 에서는 물건 자체를 쪼갤 수 있기 때문에 항상 가장 가치가 높은 물건을 선택하는 것이  
최적해를 보장한다고 증명할 수 있기 때문이다. Kn += item for sortedItems until weight > maxWeight  
  
## 참고

https://hanamon.kr/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%ED%83%90%EC%9A%95%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-greedy-algorithm/
https://ko.wikipedia.org/wiki/%ED%83%90%EC%9A%95_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98


