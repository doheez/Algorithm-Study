import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    int[] parent;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];

        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int dockingCnt = 0;

        for (int i = 0; i < P; i++) {
            int airplane = Integer.parseInt(br.readLine());
            int gate = find(airplane);

            // 차선책이 0번을 가리키고 있으면 더이상 도킹이 불가능한 상태
            if (gate == 0) {
                break;
            }
            dockingCnt++;

            // 만약, g번 비행기를 g번 게이트에 도킹할 수 없다면
            // g-1번 게이트에 차선책으로 도킹시킴.
            union(gate, gate - 1);
        }

        System.out.println(dockingCnt);
        br.close();
    }

    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
