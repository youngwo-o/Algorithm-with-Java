package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656 {

    static class Pair {
        int h, w;

        public Pair(int h, int w) {
            this.h = h;
            this.w = w;
        }
    }

    static int N, W, H;
    static int[][] map;
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for(int tc = 1; tc <= T; ++tc) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < H; ++i) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < W; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution();

            System.out.println("#" + tc + " " + ans);
        }
    }

    private static void solution() {
        for(int i = 0; i < W; ++i) {
            throwBall(i, 1, map);
        }
    }

    private static void throwBall(int w, int cnt, int[][] map) {
        int[][] newMap = new int[H][W];
        for(int i = 0; i < H; ++i) {
            newMap[i] = Arrays.copyOf(map[i], W);
        }

        for(int i = 0; i < H; ++i) {
            if (newMap[i][w] != 0) {
                popBlock(i, w, newMap);
                break;
            }
        }

        if(cnt == N) {
            int res = 0;
            for(int i = 0; i < H; ++i) {
                for(int j = 0; j < W; ++j) {
                    if(newMap[i][j] != 0) res++;
                }
            }
            ans = Math.min(ans, res);
            return;
        }

        for(int i = 0; i < W; ++i) {
            throwBall(i, cnt + 1, newMap);
        }
    }

    private static void downBlock(int[][] map) {
        for(int j = 0; j < W; ++j) {
            Queue<Integer> q = new ArrayDeque<>();
            for(int i = H - 1; i >= 0; --i) {
                if(map[i][j] != 0) q.add(map[i][j]);
                map[i][j] = 0;
            }
            int i = H - 1;
            while(!q.isEmpty()) {
                map[i--][j] = q.poll();
            }
        }
    }

    private static void popBlock(int h, int w, int[][] map) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(h, w));

        while(!q.isEmpty()) {
            Pair now = q.poll();
            int len = map[now.h][now.w] - 1;
            map[now.h][now.w] = 0;

            for (int i = 0; i < 4; ++i) {
                for(int j = 1; j <= len; ++j) {
                    int nh = now.h + d[i][0] * j;
                    int nw = now.w + d[i][1] * j;

                    if(nh < 0 || nh >= H || nw < 0 || nw >= W) continue;

                    if(map[nh][nw] != 0) q.add(new Pair(nh, nw));
                }
            }
        }

        downBlock(map);
    }
}
