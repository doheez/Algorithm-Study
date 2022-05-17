import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ship {
    static int c, b;
    static ArrayList<Integer> c_list = new ArrayList<>();
    static ArrayList<Integer> b_list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int ans = 0;

        c = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            c_list.add(Integer.parseInt(st.nextToken()));
        }

        b = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            b_list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(c_list, Collections.reverseOrder());
        Collections.sort(b_list, Collections.reverseOrder());
        if(c_list.get(0) < b_list.get(0))
        {
            System.out.println(-1);
            return;
        }

        while (!b_list.isEmpty()) {
            int idx = 0;
            for (int i = 0; i < c;) {
                if (idx == b_list.size())
                    break;
                else if(c_list.get(i) >= b_list.get(idx)) {
                    b_list.remove(idx);
                    i++;
                }else idx++;
            }
            ans++;
        }
        System.out.println(ans);
    }

}
