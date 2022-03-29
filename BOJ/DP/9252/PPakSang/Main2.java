package BOJ.DP.LCS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main2 {

    static char[] arr1;
    static char[] arr2;

    static Integer[][] dp;

    static Stack<Character> st = new Stack<>();
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        arr1 = str1.toCharArray();
        arr2 = str2.toCharArray();

        dp = new Integer[arr1.length + 1][arr2.length + 1];


        int result = lcs(arr1.length, arr2.length);
        System.out.println(result);
        if (result == 0) {
            return;
        } else {
            int x = arr1.length;
            int y = arr2.length;


            while (x > 0 && y > 0) {


                if (dp[x][y].equals(dp[x-1][y])) {
                    x--;
                }
                else if (dp[x][y].equals(dp[x][y-1])) {
                    y--;
                }
                else {
                    st.push(str1.charAt(x-1));
                    x--;
                    y--;
                }
            }


            while(!st.isEmpty()) {
                System.out.print(st.pop());
            }
            System.out.println();
        }

    }

    static int lcs(int x, int y) {

        if (x == 0 || y == 0) {
            dp[x][y] = 0;
            return 0;
        }

        if (dp[x][y] == null) {
            dp[x][y] = 0;
            for (int i = 1; i <= arr1.length; i++) {

                for (int j = 1; j <= arr2.length; j++) {
                    if (arr1[i-1] == arr2[j-1]) {
                        dp[i][j] = lcs(i-1, j-1) + 1;

                    } else {
                        dp[i][j] = Math.max(lcs(i-1, j), lcs(i, j-1));
                    }
                }
            }
        }

        return dp[x][y];
    }

}
