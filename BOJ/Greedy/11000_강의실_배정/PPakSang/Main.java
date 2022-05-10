package BOJ.Greedy.강의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int count = 0;
    static int minEnd = Integer.MAX_VALUE;

    static class Clazz {
        int start;
        int end;

        public Clazz(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static List<Clazz> clazzes;
    static Comparator<Clazz> comparator = new Comparator<Clazz>() {
        @Override
        public int compare(Clazz o1, Clazz o2) {
            if (o1.start == o2.start) return o1.end - o2.end;
            else return o1.start - o2.start;
        }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> times = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        clazzes = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            clazzes.add(new Clazz(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }

        clazzes.sort(comparator);

        times.add(Integer.MAX_VALUE);

        for (Clazz clazz : clazzes) {
            if (clazz.start < times.peek()) {
                count++;
                times.add(clazz.end);
            } else {
                times.remove();
                times.add(clazz.end);
            }
        }

        System.out.println(count);

    }
}
