import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gerrymandering2 {
    static int N;
    static int[][] map;
    static int SUM;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                SUM += map[i][j];
            }
        }
        selectD();
        System.out.println(MIN);
    }
    static void selectD() {
        int d1 = 0;
        int d2 = 0;
        for(int i = 2; i <N; i++) {
            for(int j = 1; j < i; j++) {
                d1 = j;
                d2 = i-d1;
                selectPosition(d1,d2);
                if(d1 == d2)
                    continue;
                selectPosition(d2, d1);
            }
        }
    }

    static void selectPosition(int d1, int d2) {

        for(int x = 1; x < N-1; x++) {
            for(int y = 1; y <N-1; y++) {
                if(y-d1 < 0)
                    continue;
                if(y+d2 > N)
                    continue;
                if(x + (d1 + d2) > N )
                    continue;

                calMin(x,y,d1,d2);
            }
        }
    }

    static void calMin(int x, int y, int d1, int d2) {
        int min = Integer.MAX_VALUE;
        int max = -1;

        int sum1 = cal1(x, y, d1, d2);
        int sum2 = cal2(x, y, d1, d2);
        min = Math.min(sum1, sum2);
        max = Math.max(sum1, sum2);

        int sum3 = cal3(x, y, d1, d2);
        min = Math.min(min, sum3);
        max = Math.max(max, sum3);

        int sum4 = cal4(x, y, d1, d2);
        min = Math.min(min, sum4);
        max = Math.max(max, sum4);

        int sum5 = SUM - (sum1 + sum2 + sum3 + sum4);
        min = Math.min(min, sum5);
        max = Math.max(max, sum5);

        MIN = Math.min(max-min, MIN);
    }

    static int cal1(int x, int y, int d1, int d2) {
        int temp = 0;
        int sum1 = 0;
        for (int i = 0; i < x + d1; i++) {
            if (i > x - 1)
                temp++;
            for (int j = 0; j <= y - temp; j++) {
                sum1 += map[i][j];
            }
        }

        return sum1;
    }

    static int cal2(int x, int y, int d1, int d2) {
        int sum2 = 0;
        int temp = 0;
        for (int i = 0; i <= x + d2; i++) {
            if (i > x)
                temp++;
            for (int j = y + temp + 1; j < N; j++) {
                sum2 += map[i][j];
            }
        }

        return sum2;
    }

    static int cal3(int x, int y, int d1, int d2) {
        int sum3 = 0;
        int temp = d1 + 1;

        for (int i = x + d1; i < N; i++) {
            if (i <= x + d1 + d2)
                temp--;
            for (int j = 0; j < y - temp; j++) {
                sum3 += map[i][j];
            }
        }

        return sum3;
    }

    static int cal4(int x, int y, int d1, int d2) {
        int sum4 = 0;
        int temp = d2 + 1;

        for (int i = x + d2 + 1; i < N; i++) {
            if (i <= x + d1 + d2 + 1)
                temp--;
            for (int j = y + temp; j < N; j++) {
                sum4 += map[i][j];
            }
        }

        return sum4;
    }

}