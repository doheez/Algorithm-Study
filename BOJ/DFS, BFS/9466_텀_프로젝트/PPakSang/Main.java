package BOJ.DFSBFS.텀프로젝트.개선;

import java.util.*;
import java.io.*;

public class Main {
    static int[] select;
    static int[] hasGroup;
    static int cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] temp = br.readLine().split(" ");
            cnt = N;

            select = new int[N];
            hasGroup = new int[N];

            for (int j = 0; j < N; j++) {
                select[j] = Integer.parseInt(temp[j])-1;
            }

            for (int j = 0; j < N; j++) {
                if (hasGroup[j] == 0){
                    hasGroup[j] = 2;
                    if (checkGroup(select[j]) != -1) {
                        hasGroup[j] = 1;
                        cnt--;
                    } else {
                        hasGroup[j] = -1;
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    static int checkGroup(int c) {
        if (hasGroup[c] == 1 || hasGroup[c] == -1) return -1;
        if (hasGroup[c] == 2) return c;

        hasGroup[c] = 2;

        int result = checkGroup(select[c]);

        if (result == -1) {
            hasGroup[c] = -1;
            return -1;
        } else {
            hasGroup[c] = 1;
            cnt--;
            if (c == result) {
                return -1;
            }
            return result;
        }
    }

}

