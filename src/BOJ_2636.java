import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static int[][] arr;
    static int[][] d = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static Queue<Pair> cheezeQ;
    static int cnt = 0, ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        for(int i = 0; i < R; ++i) {
            st = new StringTokenizer(in.readLine()," ");
            for(int j = 0; j < C; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        System.out.println(cnt + "\n" + ans);
    }

    private static void solution() {
        while(true) {
            findSpace();
            if(cheezeQ.isEmpty())
                break;

            cnt++;
            ans = cheezeQ.size();
            while (!cheezeQ.isEmpty()) {
                Pair cheeze = cheezeQ.poll();
                arr[cheeze.r][cheeze.c] = 0;
            }
        }
    }

    private static void findSpace() {
        Queue<Pair> q = new LinkedList<>();
        cheezeQ = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        q.add(new Pair(0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Pair now = q.poll();

            for(int i = 0; i < 4; ++i) {
                int nr = now.r + d[i][0];
                int nc=  now.c + d[i][1];

                if(nr < 0 || nr >=R || nc < 0 || nc >=C || visited[nr][nc]) continue;

                if(arr[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.add(new Pair(nr, nc));
                }
                else if(arr[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    cheezeQ.add(new Pair(nr, nc));
                }
            }
        }
    }
}
