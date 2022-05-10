import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class lectureRoom {
    static int n;
    static PriorityQueue<Integer> room;
    static PriorityQueue<Lecture> lec;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        room = new PriorityQueue<>();
        lec = new PriorityQueue<>();

        for(int i = 0; i <n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lec.add(new Lecture(a,b));
        }

        room.add(lec.poll().t);
        for(int i = 1; i < n; i++) {
            if(room.peek() <= lec.peek().s)
                room.poll();

            room.add(lec.poll().t);
        }

        System.out.println(room.size());
    }


    static class Lecture implements Comparable<Lecture>{
        int s;
        int t;
        public Lecture(int s, int t) {
            super();
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.s < o.s)
                return -1;
            else if(this.s == o.s)
                return 0;
            else
                return 1;

        }
    }

}
