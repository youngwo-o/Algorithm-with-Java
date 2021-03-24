import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1600  {

    private static class Monkey {
        int r, c, cnt, hMove;

        public Monkey(int r, int c, int cnt, int hMove) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.hMove = hMove;
        }
    }

    static int K, R, C;
    static int[][] arr;
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] hd = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        for (int i = 0; i < R; ++i) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < C; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }

    private static int solution() {
        int ans = Integer.MAX_VALUE;
        Queue<Monkey> q = new LinkedList<>();
        int[][][] visited = new int[R][C][K + 1];

        q.add(new Monkey(0, 0, 0, 0));
        visited[0][0][0] = 0;

        while(!q.isEmpty()) {
            Monkey now = q.poll();

            if(now.r == R - 1 && now.c == C - 1) {
                ans = Math.min(ans, now.cnt);
                continue;
            }

            for(int i = 0; i < 4; ++i) {
                int nr = now.r + d[i][0];
                int nc = now.c + d[i][1];

                if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                if(arr[nr][nc] == 0 && (visited[nr][nc][now.hMove] == 0 || visited[nr][nc][now.hMove] > now.cnt + 1)) {
                    visited[nr][nc][now.hMove] = now.cnt + 1;
                    q.add(new Monkey(nr, nc, now.cnt + 1,now.hMove));
                }
            }

            if(now.hMove < K) {
                for(int i = 0; i < 8; ++i) {
                    int nr = now.r + hd[i][0];
                    int nc = now.c + hd[i][1];

                    if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                    if(arr[nr][nc] == 0 && (visited[nr][nc][now.hMove + 1] == 0 || visited[nr][nc][now.hMove + 1] > now.cnt + 1)) {
                        visited[nr][nc][now.hMove + 1] = now.cnt + 1;
                        q.add(new Monkey(nr, nc, now.cnt + 1,now.hMove + 1));
                    }
                }
            }
        }

        if(ans == Integer.MAX_VALUE) ans = -1;
        return ans;
    }
}
