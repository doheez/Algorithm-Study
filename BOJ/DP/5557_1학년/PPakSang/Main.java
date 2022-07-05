package BOJ.DP.일학년;

import java.util.*;
import java.io.*;

public class Main {

    static int[] nums;
    static long ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] temp = br.readLine().split(" ");
        nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(temp[i]);
        }

        Map<Integer, Long> map = new HashMap<>();
        map.put(nums[0], 1L);

        for (int i = 1; i < N-1; i++) {
            Map<Integer, Long> nMap = new HashMap<>();

            for (Integer key : map.keySet()) {
                int nKey = key+nums[i];
                int nKey2 = key-nums[i];

                if (nKey <= 20) {
                    if (nMap.get(nKey) == null) {
                        nMap.put(nKey, map.get(key));
                    } else {
                        nMap.put(nKey, nMap.get(nKey)+map.get(key));
                    }
                }

                if (nKey2 >= 0) {
                    if (nMap.get(nKey2) == null) {
                        nMap.put(nKey2, map.get(key));
                    } else {
                        nMap.put(nKey2, nMap.get(nKey2)+map.get(key));
                    }
                }
            }

            map = nMap;
        }

        System.out.println(map.getOrDefault(nums[N-1], 0L));
    }
}
