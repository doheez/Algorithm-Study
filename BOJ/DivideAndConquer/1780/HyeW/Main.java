import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   static StringBuilder sb = new StringBuilder();
   static int[][] paper;
   static int N;
   static int[] ans = new int[3];

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      st = new StringTokenizer(br.readLine(), " ");
      N = Integer.parseInt(st.nextToken());

      paper = new int[N][N];

      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine(), " ");
         for (int j = 0; j < N; j++)
            paper[i][j] = Integer.parseInt(st.nextToken());
      }

      br.close();
   
      cut(0,0,N);
      
      for(int i = 0; i<3; i++)
         sb.append(ans[i]).append("\n");
      
      System.out.print(sb);
   }

   static void cut(int row, int col, int n) {
   
      int first = paper[row][col];
      int a = n/3;

      for (int i = row; i < row+n; i++) {
         for (int j = col; j < col+n; j++) {
            if (first != paper[i][j]){
               for(int k = 0; k<3;k++)
                  for(int l = 0; l<3; l++)
                     cut((k*a)+row,(l*a)+col,a);
               return;
            }
         }
      }
      
      ans[first+1]++;
   }
}