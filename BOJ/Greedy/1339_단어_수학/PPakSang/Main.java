package BOJ.Greedy.단어수학;

import java.util.*;
import java.io.*;

public class Main {

    static List<Long> l;
    static Map<Character, Long> num;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        l = new ArrayList<>();
        num = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < temp.length(); j++) {
                char c = temp.charAt(j);
                int location = temp.length() - j;

                long w = (long)Math.pow(10, location-1);

                if (!num.containsKey(c)) {
                    num.put(c, w);
                } else {
                    num.put(c, num.get(c)+w);
                }
            }
        }

        long answer = 0;
        int max = 9;

        for (Character key : num.keySet()) {
            l.add(num.get(key));
        }

        l.sort(Comparator.reverseOrder());

        for (int i = 0; i < l.size(); i++) {
            answer += l.get(i)*max;
            max--;
        }

        System.out.println(answer);
    }
}
