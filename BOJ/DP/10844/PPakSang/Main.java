package BOJ.DP.쉬운계단수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] result = new long[n+1][10];

        result[1][0] = 0L;
        for (int i = 1; i < 10; i++) {
            result[1][i] = 1L;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    result[i][j] = (result[i-1][1]) % 1000000000;
                }
                else if(j == 9) {
                    result[i][j] = (result[i-1][8]) % 1000000000;
                }
                else {
                    result[i][j] = (result[i-1][j-1] % 1000000000) + (result[i-1][j+1] % 1000000000) ;
                }
            }
        }

        Long temp = 0L;

        for (int i = 0; i < 10; i++) {
            temp += result[n][i];
        }

        System.out.println(temp % 1000000000);

    }
}
