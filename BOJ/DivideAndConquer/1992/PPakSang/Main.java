package BOJ.DivNConq.쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] screen;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        screen = new int[n][n];

        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<n; j++) {
                screen[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        compress(0, n-1, 0, n-1);
    }

    static void compress(int cs, int ce, int rs, int re) {
        int temp = screen[rs][cs];
        if(cs == ce) {
            System.out.print(temp);
            return;
        }
        for (int i=rs; i<=re; i++) {
            for (int j=cs; j<=ce; j++) {
                if (screen[i][j] != temp) {
                    System.out.print("(");
                    compress(cs, (cs+ce)/2, rs, (rs+re)/2);
                    compress((cs+ce)/2+1, ce, rs, (rs+re)/2);
                    compress(cs, (cs+ce)/2, (rs+re)/2+1, re);
                    compress((cs+ce)/2+1, ce, (rs+re)/2+1, re);
                    System.out.print(")");
                    return;
                }
            }
        }
        System.out.print(temp);
    }
}
