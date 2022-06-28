import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GoDown {
    static int N;
    static int[][] dp_max;
    static int[][] dp_min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp_max = new int[2][3];
        dp_min = new int[2][3];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 3; j++)
            dp_max[0][j] = Integer.parseInt(st.nextToken());
        dp_min[0] = Arrays.copyOf(dp_max[0], dp_max[0].length);

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                dp_max[1][j] = Integer.parseInt(st.nextToken());
            dp_min[1] = Arrays.copyOf(dp_max[1], dp_max[1].length);
            countSteps();
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp_max[0][i]);
            min = Math.min(min, dp_min[0][i]);
        }

        System.out.println(max + " " + min);
    }

    static void countSteps() {

        for (int j = 0; j < 3; j++) {
            int max = dp_max[0][j];
            int min = dp_min[0][j];
            if (j != 0) {
                max = Math.max(dp_max[0][j - 1], max);
                min = Math.min(dp_min[0][j - 1], min);
            }

            if (j != 2) {
                max = Math.max(dp_max[0][j + 1], max);
                min = Math.min(dp_min[0][j + 1], min);
            }

            dp_max[1][j]  = dp_max[1][j] + max;
            dp_min[1][j]  = dp_min[1][j]  + min;
        }
        dp_max[0] = Arrays.copyOf(dp_max[1], dp_max[1].length);
        dp_min[0] = Arrays.copyOf(dp_min[1], dp_min[1].length);
    }
}
