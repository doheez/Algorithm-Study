package BOJ.구현.게리맨더링2;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int answer = Integer.MAX_VALUE;
    static int totalPopulation = 0;
    static int[][] populationMap;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        populationMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                populationMap[i][j] = Integer.parseInt(temp[j]);
                totalPopulation += Integer.parseInt(temp[j]);
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N) continue;
                        if (y - d1 < 0 || y + d2 >= N) continue;

                        calcSpace(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(answer);

    }

    static void calcSpace(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N][N];
        int[] result = new int[5];
        int except5 = 0;

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y +d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (border[i][j]) break;
                result[0] += populationMap[i][j];
                except5 += populationMap[i][j];
            }
        }

        // 2 구역 인구수
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (border[i][j]) break;
                result[1] += populationMap[i][j];
                except5 += populationMap[i][j];
            }
        }

        // 3 구역 인구수
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                result[2] += populationMap[i][j];
                except5 += populationMap[i][j];
            }
        }

        // 4 구역 인구수
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                result[3] += populationMap[i][j];
                except5 += populationMap[i][j];
            }
        }

            result[4] = totalPopulation - except5;

            Arrays.sort(result);

            answer = Math.min(answer, result[4] - result[0]);
    }
}
