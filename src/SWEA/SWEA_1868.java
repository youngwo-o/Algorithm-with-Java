package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1868 {

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, cnt;
    static char[][] arr;
    static boolean[][] visited;
    static int[][] d = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(in.readLine());
            cnt = 0;
            arr = new char[N][N];
            visited= new boolean[N][N];

            for (int i = 0; i < N; ++i) {
                arr[i] = in.readLine().toCharArray();
            }

            solution();

            System.out.println("#" + tc + " " + cnt);
        }
    }

    private static void solution() {
        Queue<Pair> q = new ArrayDeque<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (arr[i][j] == '.' && !visited[i][j] && isZero(i, j)) {
                    q.add(new Pair(i, j));
                    visited[i][j] = true;
                    cnt++;

                    while (!q.isEmpty()) {
                        Pair now = q.poll();

                        for (int k = 0; k < 8; ++k) {
                            int nr = now.r + d[k][0];
                            int nc = now.c + d[k][1];

                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                            if (arr[nr][nc] == '.' && !visited[nr][nc]) {
                                if (isZero(nr, nc)) q.add(new Pair(nr, nc));
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if(arr[i][j] == '.' && !visited[i][j]) cnt++;
            }
        }
    }

    private static boolean isZero(int r, int c) {
        for(int i = 0; i < 8; ++i) {
            int nr = r + d[i][0];
            int nc = c + d[i][1];

            if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if(arr[nr][nc] == '*') return false;
        }
        return true;
    }


}
