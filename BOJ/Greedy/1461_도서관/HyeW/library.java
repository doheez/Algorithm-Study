import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class library {
    static int n, m, distance;
    static PriorityQueue<Integer> neg;
    static PriorityQueue<Integer> pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        neg = new PriorityQueue<>(Collections.reverseOrder());
        pos = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp < 0)
                neg.add(-temp);
            else
                pos.add(temp);
        }

        int nremain = neg.size() % m;
        int premain = pos.size() % m;

        // Max값을 마지막으로 감
        if (neg.size() > 0 && pos.size() > 0) {
            if (neg.peek() > pos.peek()) {
                popMax(neg);
            } else {
                popMax(pos);
            }
        }else {//음수, 양수 둘 중 하나라도 값이 없을때
            if(neg.size() == 0)
                popMax(pos);
            else
                popMax(neg);
        }

        move(premain, pos);
        move(nremain, neg);

        System.out.println(distance);
    }

    static void move(int remain, PriorityQueue<Integer> q) {
        while (q.size() > remain || q.size() > 0) {
            distance += q.peek() * 2;
            for (int i = 0; i < m; i++)
                q.poll();
        }

        if (q.size() == 0)
            return;

        distance += q.peek() * 2;

    }

    static void popMax(PriorityQueue<Integer> q) {
        distance += q.peek();
        for (int i = 0; i < m; i++) {
            q.poll();
            if (q.size() == 0)
                break;
        }
    }

}