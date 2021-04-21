package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5643 {

    static int N, M;
    static boolean[][] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for(int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(in.readLine());
            M = Integer.parseInt(in.readLine());
            adj = new boolean[N + 1][N + 1];

            for(int i = 0; i < M; ++i) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a][b] = true;
            }

            System.out.println("#" + tc + " " + solution());
        }
    }

    private static int solution() {
        for(int k = 1; k <= N; ++k) {
            for(int i = 1; i <= N; ++i) {
                for(int j = 1; j <= N; ++j) {
                    if(adj[i][k] && adj[k][j]) adj[i][j] = true;
                }
            }
        }

        int ans = 0;
        for(int i = 1; i <= N; ++i) {
            int cnt = 0;
            for(int j = 1; j <= N; ++j) {
                if(adj[i][j] || adj[j][i]) cnt++;
            }
            if(cnt == N - 1) ans++;
        }

        return ans;
    }
}
