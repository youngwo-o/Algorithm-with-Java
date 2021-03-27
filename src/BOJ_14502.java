import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, ans;
    static int[][] arr;
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0; j < M; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                if(arr[i][j] == 0) {
                    selectVirus(i, j, 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    private static void selectVirus(int r, int c, int cnt) {
        arr[r][c] = 3;
        if(cnt == 3) {
            ans = Math.max(ans, spreadVirus());
            return;
        }

        for(int i = r; i < N; ++i) {
            int j;
            if(i == r) j = c + 1;
            else j = 0;
            for(; j < M; ++j) {
                if(arr[i][j] == 0) {
                    selectVirus(i, j, cnt + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    private static int spreadVirus() {
        Queue<Pair> virus = new ArrayDeque<>();
        boolean visited[][] = new boolean[N][M];

        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                if (arr[i][j] == 2) {
                    visited[i][j] = true;
                    virus.add(new Pair(i, j));
                }
            }
        }

        while(!virus.isEmpty()) {
            Pair now = virus.poll();

            for(int i= 0; i < 4; ++i) {
                int nr = now.r + d[i][0];
                int nc = now.c + d[i][1];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;

                if(arr[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    virus.add(new Pair(nr, nc));
                }
            }
        }

        int res = 0;
        for(int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (!visited[i][j] && arr[i][j] == 0) res++;
            }
        }

        return res;
    }
}
