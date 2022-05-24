import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Airport {
    static int G, P;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G+1];
        for(int i = 0; i <= G; i++)
            parent[i] = i;

        int p, ans = 0;
        for (int i = 0; i < P; i++) {
            p = Integer.parseInt(br.readLine());
            int root = find(p);
            if(root!=0) {
                union(root, root-1);
                ans++;
            }else
                break;
        }
        System.out.println(ans);
    }

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
}