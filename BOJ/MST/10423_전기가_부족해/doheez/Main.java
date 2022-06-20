import java.io.*;
import java.util.*;

public class Main {
    int N, M, K;
    int[] parent;
    ArrayList<Edge> edgeList;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 수, 간선 수, 발전소 수 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // parent 배열 초기화
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        // 발전소가 있는 위치를 -1로 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int index = Integer.parseInt(st.nextToken());
            parent[index] = -1;
        }

        // 간선 입력
        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(a, b, weight));
        }

        int result = kruskal();
        System.out.println(result);
        br.close();
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (y == -1) {
                parent[x] = y;
            } else {
                parent[y] = x;
            }
        }
    }

    public int find(int x) {
        if (parent[x] == -1) {
            return -1;
        }
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public int kruskal() {
        // 간선 오름차순 정렬
        Collections.sort(edgeList);

        int sum = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);

            if (find(edge.a) != find(edge.b)) {
                sum += edge.weight;
                union(edge.a, edge.b);
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Edge implements Comparable<Edge> {
    int a;
    int b;
    int weight;

    Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        // 우선순위는 가중치가 낮은 순
        return Integer.compare(this.weight, o.weight);
    }
}