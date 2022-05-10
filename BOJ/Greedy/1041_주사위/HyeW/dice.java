import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dice {
    static long n;
    static int[] dice = new int[6];
    static int[] visited =new int[6];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long result = 0;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i <6; i++)
            dice[i] = Integer.parseInt(st.nextToken());

        //n이 1일때 예외처리
        if(n == 1)
        {
            int max = 0;
            for(int i = 0; i < 6; i++) {
                max = Math.max(dice[i], max);
                result += dice[i];
            }
            System.out.println(result - max);
            return;
        }

        //3면일때 최대
        comb(3, 0);
        result = min*4;//3면이 보이는 주사위 개수 : 4

        //2면일때 최대
        min = Integer.MAX_VALUE;
        comb(2,0);
        result += min*((n-2)*8 + 4); //2면이 보이는 주사위 개수: (n-2)*8 + 4

        //1면일때 최대
        min = Integer.MAX_VALUE;
        for(int i = 0; i < 6; i++) {
            min = Math.min(dice[i], min);
        }
        result += min*(n*n*5 - ((n-2)*8+4)*2 -(4*3));

        System.out.println(result);
    }


    //조합
    static void comb(int d, int r) {
        if(d == 0) {
            int temp = 0;
            for(int i = 0; i < 6; i++) {
                if(visited[i] == 1)
                    temp += dice[i];
            }
            min = Math.min(temp, min);
            return;
        }


        for(int i = r; i < 6; i++) {
            if(visited[i] != 0)
                continue;
            visited[i] = 1;
            visited[5-i] = -1;
            comb(d-1, r+1);
            visited[i] = 0;
            visited[5-i] = 0;
        }
    }

}
