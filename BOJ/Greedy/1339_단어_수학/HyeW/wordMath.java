import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class wordMath {
    static char[][] str;
    static PriorityQueue<Integer> arrsum = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int ans = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        str = new char[n][];
        int strlen;

        for(int i = 0; i < n; i++) {
            int count;
            str[i] = br.readLine().toCharArray();
            strlen = str[i].length;

            count = strlen -1;
            for(int j = 0; j <strlen; j++) {
                map.put(str[i][j], map.getOrDefault(str[i][j], 0)+(int) Math.pow(10, count));
                count--;
            }
        }

        //HashMap 값을 꺼내 maxheap에 넣어준다.
        Iterator<Character> it = map.keySet().iterator();

        while(it.hasNext()) {
            char c = it.next();
            arrsum.add(map.get(c));
        }

        int i = 9;
        while(!arrsum.isEmpty()) {
            int value = arrsum.poll();
            ans += value*i;
            i--;
        }
        System.out.println(ans);
    }


}
