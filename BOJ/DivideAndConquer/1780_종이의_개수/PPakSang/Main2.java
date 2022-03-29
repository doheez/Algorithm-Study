package BOJ.DivNConq.종이의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int[][] paper;
    static int[] result = new int[3];
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

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);

    }

    static void countPaper(int xStart, int xEnd, int yStart, int yEnd) {

        int temp = paper[xStart][yStart];

        if (xEnd == 1) {
            result[temp+1]++;
            return;
        }

        for (int i=xStart; i<xStart+xEnd; i++) {
            for (int j=yStart; j<yStart+yEnd; j++) {
                if (temp != paper[i][j]) {

                    int xDis = (xEnd) / 3 ;
                    int yDis = (yEnd) / 3;

                    countPaper(xStart, xDis, yStart, yDis);
                    countPaper(xStart + xDis, xDis, yStart, yDis);
                    countPaper(xStart + xDis*2, xDis, yStart, yDis);

                    countPaper(xStart, xDis, yStart + yDis, yDis);
                    countPaper(xStart + xDis, xDis, yStart + yDis, yDis);
                    countPaper(xStart + xDis*2, xDis, yStart + yDis, yDis);

                    countPaper(xStart, xDis, yStart + yDis*2, yDis);
                    countPaper(xStart + xDis, xDis, yStart + yDis*2, yDis);
                    countPaper(xStart + xDis*2, xDis, yStart + yDis*2, yDis);
                    return;
                }
            }
        }

        result[temp+1]++;
        return;

    }
}
