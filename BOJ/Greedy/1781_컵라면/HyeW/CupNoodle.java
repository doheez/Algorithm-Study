import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CupNoodle {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Integer> solve = new PriorityQueue<>();
        int ans = 0;
        int n = Integer.parseInt(br.readLine());
        ArrayList<Problem> problem = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            problem.add(new Problem(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        problem.sort(null);

        for(Problem cur : problem) {
            int day = solve.size();
            if(day < cur.day) {
                solve.add(cur.cup);
            }else if(day == cur.day) {
                if(solve.peek() < cur.cup) {
                    solve.poll();
                    solve.add(cur.cup);
                }
            }
        }

        while(!solve.isEmpty()) {
            ans += solve.poll();
        }
        System.out.println(ans);
    }

    static class Problem implements Comparable<Problem>{
        int day;
        int cup;
        public Problem(int day, int cup) {
            super();
            this.day = day;
            this.cup = cup;
        }

        @Override
        public int compareTo(Problem o) {
            return this.day - o.day;
        }
    }
}
