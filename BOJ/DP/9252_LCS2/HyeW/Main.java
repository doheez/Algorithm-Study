import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
   static StringBuilder sb = new StringBuilder();
   static int[][] dp;
   static String str1, str2;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      str1 = br.readLine();
      str2 = br.readLine();
      int n = str1.length();
      int m = str2.length();

      dp = new int[n + 1][m + 1];

      for (int i = 1; i <= n; i++)
         for (int j = 1; j <= m; j++) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
               dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
               dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
         }

      print(n, m);

      System.out.println(dp[n][m]);
      System.out.println(sb);
   }

   static void print(int i, int j) {
      if (dp[i][j] == 0)
         return;

      if (dp[i][j - 1] == dp[i][j]) {
         print(i, j - 1);
      } else if (dp[i - 1][j] == dp[i][j]) {
    	  print(i-1, j);
      } else{
    	  sb.insert(0, str1.charAt(i - 1));
    	  print(i - 1, j - 1);
      }

   }

}
