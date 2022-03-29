import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   static int[][] bag;
   static int n, w;
   static item[] it;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      int max = 0;

      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());

      bag = new int[n + 1][w + 1];
      it = new item[n + 1];

      for (int i = 1; i <= n; i++) {
         st = new StringTokenizer(br.readLine());
         int temp_w = Integer.parseInt(st.nextToken());
         int temp_v = Integer.parseInt(st.nextToken());
         it[i] = new item(temp_w,temp_v);
      }

      for (int i = 1; i <= n; i++) {
         int temp_w = it[i].w;
         int temp_v = it[i].v;
         for (int j = 1; j <= w; j++) {

            if ((j - temp_w) >= 0) {
               bag[i][j] = Math.max(bag[i-1][j - temp_w] + temp_v, bag[i-1][j]);
            } else {
               bag[i][j] = bag[i-1][j];
            }
             max = Math.max(max, bag[i][j]);
         }
      }

      System.out.println(max);
   }

   static class item {
      int w;
      int v;

      public item(int w, int v) {
         this.w = w;
         this.v = v;
      }
   }
}
