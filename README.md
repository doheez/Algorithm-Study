## 📘 스터디 규칙
1. 매주 화요일 오후 7시 대면 스터디 진행
2. 매주 3개의 문제를 풀이
3. 본인 코드 설명은 템플릿을 참고하여 `README.md`에 기록
4. 다른 사람의 코드를 보고 코드 리뷰할 것이 있으면 PR에 코멘트 남기기
<br>

## 📘 문제집
<details>
<summary>1주차 ~ 5주차</summary>
<br>
  
||날짜|알고리즘|출처|문제1|문제2|문제3|
|--|--|--|--|--|--|--|
|**1주차**|03.01. ~ 03.07.|분할 정복|백준|[종이의 개수](https://www.acmicpc.net/problem/1780)|[쿼드트리](https://www.acmicpc.net/problem/1992)||
|**2주차**|03.08. ~ 03.14.|DP|백준|[쉬운 계단 수](https://www.acmicpc.net/problem/10844)|[가장 긴 증가하는 부분 수열](https://www.acmicpc.net/problem/11053)|[오르막 수](https://www.acmicpc.net/problem/11057)|
|**3주차**|03.15. ~ 03.21.|DP|백준|[LCS](https://www.acmicpc.net/problem/9251)|[가장 긴 증가하는 부분 수열 4](https://www.acmicpc.net/problem/14002)|[줄세우기](https://www.acmicpc.net/problem/2631)|
|**4주차**|03.22. ~ 03.28.|DP|백준|[LCS 2](https://www.acmicpc.net/problem/9252)|[평범한 배낭](https://www.acmicpc.net/problem/12865)|[내리막 길](https://www.acmicpc.net/problem/1520)|
  
</details>
<br>

## 📘 폴더 구조
[문제 출처] / [알고리즘명] / [문제 제목] / [이름]

<br><br>

## 📘 깃허브 사용법
### 전체적인 흐름
1. 매주 대면 회의에서 문제 선정 직후, 한 사람이 **main 브랜치에 새로운 문제 폴더를 생성**한다.
2. main 브랜치에서 본인 이름으로 **각자 브랜치를 생성**한다.
3. 본인 브랜치에서 첫 `commit`, `push` 후 깃허브 페이지에서 **PR을 생성**한다. (레포지토리에서 `Compare & pull request` 버튼 클릭)
4. 한 번 생성한 PR은 일주일간 유효하며, 다음 회의 시작 시 스터디원들과 함께 `merge`한다.
<br>

### Pull Request
- PR 제목은 `[해당 주]-[본인 이름]`으로 한다.<br>
  예시: ```week1-doheez```
- merge base가 main임을 확인한다.
- Assignees에 본인을 태그하고, Labels에 해당 주에 사용하는 알고리즘을 태그한다.
- 덧붙일 코멘트가 있다면 자유롭게 작성한다.
<br>

### Commit Convention
- 새로운 문제 파일 추가 시: `Create [문제 번호] [문제 제목]`
  ```
  Create 1003 피보나치 함수
  ```

- 기존 코드 수정 시: `Modify [문제 번호] [문제 제목]`
  ```
  Modify 1003 피보나치 함수
  ```
<br>

### 코드 설명 방법
[`template.md`](https://github.com/doheez/Algorithm-Study/blob/59b14034ccb576d7a2a7935d5859da0f38cfeb3d/template.md)를 참고하여 `README.md`를 작성하고 문제 풀이 코드와 동일한 폴더에 올린다.
<br><br>

### 코드 리뷰
PR에 직접 코멘트를 남겨도 좋고, 코드 일부분에다 리뷰를 해도 된다.
