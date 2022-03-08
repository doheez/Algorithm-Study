import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   static StringBuilder sb = new StringBuilder();
   static int N;
   static char[][] map;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      
      map = new char[N][N];
      
      for(int i = 0; i <N; i++) {
            map[i] = br.readLine().toCharArray();
      }
      
      br.close();
      
      recur(0,0,N);
      
      System.out.println(sb);
      
   }
   
   static void recur(int row, int col, int n) {
      char first = map[row][col];
      int a = n/2;
      
      for(int i = row; i <n+row; i++) 
         for(int j = col; j<n+col; j++) 
            if(first != map[i][j])
            {
            	sb.append('(');
               for(int k = 0; k <2; k++)
                  for(int l = 0; l <2; l++) {
                     recur((a*k)+row,(a*l)+col,a);
                 
                  }
               sb.append(')');
               return;
            }
            
      sb.append(first);
      
   }
}