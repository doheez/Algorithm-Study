import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class cardSort {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> card = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int ans = 0, result = 0;

        for(int i = 0; i < n; i++) {
            card.add(Integer.parseInt(br.readLine()));
        }

        while(card.size()!=1) {
            ans = card.poll() + card.poll();
            result+= ans;
            card.add(ans);
        }

        System.out.println(result);
    }

}
