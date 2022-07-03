package BOJ.DP.이모티콘;

import java.util.*;
import java.io.*;

class State {
    int chat;
    int clipBoard;
    int time;

    public State(int chat, int clipBoard, int time) {
        this.chat = chat;
        this.clipBoard = clipBoard;
        this.time = time;
    }
}

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[][] dp = new boolean[N+1][N+1];

        Queue<State> q = new LinkedList<>();

        q.add(new State(1, 0, 0));

        while(!q.isEmpty()) {
            State state = q.poll();

            if (state.chat == N) {
                System.out.println(state.time);
                break;
            }
            if (dp[state.chat][state.clipBoard]) continue;
            dp[state.chat][state.clipBoard] = true;

            q.add(new State(state.chat, state.chat, state.time+1));

            if (state.clipBoard > 0 && (state.chat+state.clipBoard) <= N)
                q.add(new State(state.chat+state.clipBoard, state.clipBoard, state.time+1));

            if (state.chat-1 > 0)
                q.add(new State(state.chat-1, state.clipBoard, state.time+1));
        }

        br.close();

    }
}
