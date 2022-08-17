import java.io.*;
import java.util.*;

public class GearWheel {
    static char[][] gear;
    static final int N = 4;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gear = new char[4][];

        for(int i = 0; i < 4; i++) {
            gear[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i <n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            rotation(k-1,d);
        }
        int result = 0;
        for(int i = 0; i < N; i++) {
            if(gear[i][0] == '1')
                result += Math.pow(2, i);
        }

        System.out.println(result);
    }

    static void rotation(int k, int d) {
        int[] gears = new int[4];
        gears[k] = d;
        //오른쪽보는 for문
        for(int i = k; i < N-1; i++) {
            //같은 극일 때
            if(gear[i][2] == gear[i+1][6])
                break;
            gears[i+1] = gears[i]*(-1);
        }

        //왼쪽보는 for문
        for(int i = k; i > 0; i--) {
            if(gear[i][6] == gear[i-1][2])
                break;
            gears[i-1] = gears[i]*(-1);
        }

        for(int i = 0; i < N; i++) {
            if(gears[i]!=0)
                rotate(i,gears[i]);
        }
    }

    static void rotate(int k, int d) {
        char[] gearTemp = gear[k].clone();

        for(int i = 0; i < 8; i++) {
            if(i == 7 && d == -1) {
                gear[k][i] = gearTemp[0];
                continue;
            }
            if(i == 0 && d == 1) {
                gear[k][i] = gearTemp[7];
                continue;
            }

            gear[k][i] = gearTemp[i-d];
        }
    }

}
