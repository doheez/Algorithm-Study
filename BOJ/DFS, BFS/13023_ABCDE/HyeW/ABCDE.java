import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ABCDE {
    static int n, m;
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n];
        for(int i = 0; i < n; i++)
            list[i] = new ArrayList<Integer>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        visited = new boolean[n];
        int result = 0;
        for(int i = 0; i < n; i++) {
            visited[i] = true;
            result = dfs(1, i);
            visited[i] = false;
            if(result == 1) {
                System.out.println(result);
                return;
            }
        }
        System.out.println(result);

    }

    static int dfs(int d, int a) {
        int result = 0;
        if(d  == 5)
            return 1;

        for(int i : list[a]) {
            if(visited[i])
                continue;

            visited[i] = true;
            result = dfs(d+1, i);
            if(result == 1)
                return 1;
            visited[i] = false;
        }

        return result;

    }

}
