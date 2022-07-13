package BOJ.DFSBFS.이분그래프;

import java.util.*;
import java.io.*;

class Node {
    int num;
    int group;

    Node(int num, int group) {
        this.num = num;
        this.group = group;
    }
}

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < T; i++) {
            String[] temp = br.readLine().split(" ");
            int V = Integer.parseInt(temp[0]);
            int N = Integer.parseInt(temp[1]);

            int[] group = new int[V+1];
            List<Integer>[] conn = new List[V+1];
            int cnt = 1;

            for (int k = 0; k <= V; k++) {
                conn[k] = new LinkedList<Integer>();
            }
            for (int j = 0; j < N; j++) {
                temp = br.readLine().split(" ");
                int n1 = Integer.parseInt(temp[0]);
                int n2 = Integer.parseInt(temp[1]);

                conn[n1].add(n2);
                conn[n2].add(n1);
            }



            boolean flag = true;

            for (int n = 1; n <= V; n++) {
                if (group[n] == 0) {
                    Queue<Node> q = new LinkedList<>();
                    q.add(new Node(n, 1));
                    group[n] = 1;

                    while(!q.isEmpty()) {
                        Node cur = q.poll();

                        for (Integer next : conn[cur.num]) {
                            if (group[next] == 0) {
                                group[next] = 3 - cur.group;
                                cnt++;
                                q.add(new Node(next, 3-cur.group));
                            } else if (group[next] == cur.group) flag = false;
                        }
                    }
                }
            }


            if (flag) System.out.println("YES");
            else System.out.println("NO");

        }
    }


}
