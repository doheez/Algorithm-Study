import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FirstGrade {
    static int N;
    static int[] expression;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        expression = new int[N];
        dp = new long[N-1][21];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            expression[i] = Integer.parseInt(st.nextToken());

        dp[0][expression[0]]=1;
        for(int i = 1; i < N-1; i++)
            for(int j = 0; j <21; j++) {
                if(dp[i-1][j]!=0) {
                    if(j+expression[i] <= 20) dp[i][j+expression[i]] += dp[i-1][j];
                    if(j-expression[i] >= 0) dp[i][j-expression[i]] += dp[i-1][j];
                }
            }
        System.out.println(dp[N-2][expression[N-1]]);

    }

}
