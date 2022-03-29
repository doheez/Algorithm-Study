package BOJ.DivNConq.종이의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {

    static int[][] paper;
    static int minus = 0;
    static int zero = 0;
    static int plus = 0;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        countPaper(0, n, 0, n);

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(plus);

    }

    static int countPaper(int xStart, int xEnd, int yStart, int yEnd) {

        if (xEnd == 0) {
            return paper[yStart][xStart];
        }

        int xDis = (xEnd) / 3 ;
        int yDis = (yEnd) / 3;

        int[] result = new int[9];

        result[0] = countPaper(xStart, xDis, yStart, yDis);
        result[1] = countPaper(xStart + xDis, xDis, yStart, yDis);
        result[2] = countPaper(xStart + xDis*2, xDis, yStart, yDis);

        result[3] = countPaper(xStart, xDis, yStart + yDis, yDis);
        result[4] = countPaper(xStart + xDis, xDis, yStart + yDis, yDis);
        result[5] = countPaper(xStart + xDis*2, xDis, yStart + yDis, yDis);

        result[6] = countPaper(xStart, xDis, yStart + yDis*2, yDis);
        result[7] = countPaper(xStart + xDis, xDis, yStart + yDis*2, yDis);
        result[8] = countPaper(xStart + xDis*2, xDis, yStart + yDis*2, yDis);

        int temp = result[0];
        int flag = 0;
        int tempm = 0;
        int tempz = 0;
        int tempp = 0;
        for (int i=0; i<9; i++) {
            if (temp != result[i]) {
                flag = 1;
            }
            switch (result[i]) {
                case -1:
                    tempm++;
                    break;
                case 0:
                    tempz++;
                    break;
                case 1:
                    tempp++;
                    break;
                case 2:
                    break;
            }
        }

        if (flag == 0) {
            if (xEnd == n) {
                minus += tempm/9;
                zero += tempz/9;
                plus += tempp/9;
            }
            return result[0];
        }
        else {
            minus += tempm;
            zero += tempz;
            plus += tempp;
            return 2;
        }


    }
}
