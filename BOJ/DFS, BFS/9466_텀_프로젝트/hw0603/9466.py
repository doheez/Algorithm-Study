import sys

T = int(sys.stdin.readline())

# 테스트 케이스 수 만큼 반복
for _ in range(T):
    n = int(sys.stdin.readline())
    pick = [0] + list(map(int, sys.stdin.readline().split()))  # 학생들이 선택한 데이터 저장
    checkResult = {}  # 해당 노드가 팀에 포함될 수 있는지 조사한 결과를 저장

    # 학생들 한 명 씩을 startnode로 보고 조사
    count = n
    for node in range(1, n+1):
        history = []  # DFS에서 사용할 노드 히스토리
        hisdict = {}  # 히스토리 리스트에서 in 연산 하면 너무 느림.. 해싱해서 있는지 없는지 판단하기 위해 딕셔너리 사용
        if (checkResult.get(node) is not None):
            continue  # 이미 조사한 결과가 있을 경우 skip
        history.append(node)
        hisdict[node] = True

        # DFS 탐색 진행
        while (True):
            node = pick[node]  # 다음 노드 선택
            if (checkResult.get(node) is not None):
                """
                예전에 조사된 바 있는 노드가 들어올 경우 더 이상 탐색할 필요 없음.
                기 조사된 노드가 이미 팀을 이루고 있는 경우 -> 어떤 경로로 그 노드에 도달했든 앞에 있는 노드로는 팀 구성 불가능
                기 조사된 노드가 팀을 이루지 못한다고 이미 판별된 경우 -> 역시 앞의 모든 경로는 팀 구성 불가능
                """
                for s in history:
                    checkResult[s] = False
                break

            if (hisdict.get(node)):
                """
                history에 있는 노드가 다시 들어올 경우 사이클이 형성된 것 -> 현재 탐색된 노드 중 팀 구성 가능한 노드 존재
                - node 이전에 들어온(인덱스가 더 작은) 노드들은 모두 팀 구성 불가 -> checkResult=False
                - node를 포함하여 그 이후에 들어온 노드들끼리 팀 구성 가능 -> checkResult=True
                """
                idx = history.index(node)
                for i in range(len(history)):
                    checkResult[history[i]] = False if i < idx else True
                    if (checkResult[history[i]]):
                        count -= 1
                break
                
            history.append(node)
            hisdict[node] = True

    print(count)
