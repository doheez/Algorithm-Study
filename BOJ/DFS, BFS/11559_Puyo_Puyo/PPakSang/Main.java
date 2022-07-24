package BOJ.DFSBFS.뿌요뿌요;

import javax.xml.stream.Location;
import java.util.*;
import java.io.*;

class Position {
    int col;
    int row;

    Position(int col, int row) {
        this.col = col;
        this.row = row;
    }
}

public class Main {
    static List<Character>[] field;
    static List<Character>[] nField;

    static int col, row;

    static int[][] state;
    static int[][] nState;

    static int[] dirX = new int[]{1, -1, 0, 0};
    static int[] dirY = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        row = 12;
        col = 6;

        field = new List[col];
        state = new int[col][row];

        int answer = -1;

        for (int i = 0; i < col; i++) field[i] = new LinkedList();

        for (int i = 0; i < row; i++) {
            String temp = br.readLine();
            for (int j = 0; j < col; j++) {
                field[j].add(0, temp.charAt(j));
            }
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (field[i].get(j) == '.') state[i][j] = -1;
            }
        }


        boolean changed = true;

        while (changed) {
            answer++;
            changed = false;

            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    if (state[i][j] == 0) {
                        checkPop(i, j);
                    }
                }
            }

            nField = new List[col];
            nState = new int[col][row];

            for (int i = 0; i < col; i++) nField[i] = new LinkedList();

            for (int i = 0; i < col; i++) {
                int poped = 0;
                for (int j = 0; j < row; j++) {
                    if (state[i][j] == 1) {
                        poped++;
                        changed = true;
                    } else {
                        char cur = field[i].get(j);
                        nField[i].add(cur);
                    }
                }

                for (int k = 0; k < row-poped; k++) nField[i].add('.');
            }

            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    if (nField[i].get(j) == '.') nState[i][j] = -1;
                }
            }

            state = nState;
            field = nField;
        }

        System.out.println(answer);
    }

    static void checkPop(int c, int r) {
        char targetChar = field[c].get(r);

        int cnt = 1;

        Queue<Position> q = new LinkedList<>();
        List<Position> history = new LinkedList<>();

        Position s = new Position(c, r);
        q.add(s);
        history.add(s);

        state[c][r] = 2;

        while(!q.isEmpty()) {
            Position cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nCol = cur.col + dirX[i];
                int nRow = cur.row + dirY[i];

                if ((0<=nCol && nCol<col) && (0<=nRow && nRow<row)
                && state[nCol][nRow] == 0 && field[nCol].get(nRow) == targetChar) {
                    cnt++;
                    state[nCol][nRow] = 2;

                    Position nPosition = new Position(nCol, nRow);
                    q.add(nPosition);
                    history.add(nPosition);
                }
            }
        }

        if (cnt >= 4) {
            for (Position p : history) {
                state[p.col][p.row] = 1;
            }
        } else {
            for (Position p : history) {
                state[p.col][p.row] = -1;
            }
        }

    }
}
