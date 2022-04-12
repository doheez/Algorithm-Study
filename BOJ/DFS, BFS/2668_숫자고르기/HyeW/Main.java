import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class select_number {
    static int[] arr;
    static int n, cnt, sum;
    static int start;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            if(visited[i])
                continue;

            start = i;
            check(i);
        }

        System.out.println(sum);
        for(int i = 1; i <=n; i++) {
            if(visited[i])
                System.out.println(i);
        }
    }

    static void check(int p) {
        cnt++;

        if(arr[p] == start) {
            sum+=cnt;
            cnt = 0;

            //출력을 위한 코드
            visited[p] = true;
            p = arr[p];

            while(arr[p] != start) {
                visited[p] = true;
                p = arr[p];
            }
            return;
        }

        if(cnt == (n - sum)) {
            cnt = 0;
            return;
        }

        check(arr[p]);

    }
}