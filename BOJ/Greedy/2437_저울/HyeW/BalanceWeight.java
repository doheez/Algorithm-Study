import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BalanceWeight {
    static int n;
    static int[] weight;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            weight[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(weight);

        int max = 0;
        for(int i = 0; i < n; i++) {
            if(max + 1 >= weight[i]) {
                max += weight[i];
            }else {
                break;
            }
        }

        System.out.println(max+1);
    }
}
