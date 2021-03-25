import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_9205 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static ArrayList<Pair> m;
    static int[][] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for(int tc = 1; tc <= T; ++tc) {
            n = Integer.parseInt(in.readLine());
            m = new ArrayList<>();
            adj = new int[102][102];

            for(int i = 0; i < n + 2; ++i) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                m.add(new Pair(x, y));
            }

            if(solution()) System.out.println("happy");
            else System.out.println("sad");
        }
    }

    private static boolean solution() {
        for(int i = 0; i < n + 2; ++i) {
            for(int j = i + 1; j < n + 2; ++j) {
                adj[i][j] = adj[j][i] = 102;
            }
        }

        for(int i = 0; i < n + 2; ++i) {
            for(int j = i + 1; j < n + 2; ++j) {
                if(isAvailable(i, j)) adj[i][j] = adj[j][i] = 1;
            }
        }

        //모든 정점 간에 이동이 가능한지 판단
        for(int k = 0; k < n + 2; ++k) {
            for(int i = 0; i < n  + 2; ++i) {
                for(int j = 0; j < n + 2; ++j) {
                    if(adj[i][j] > adj[i][k] + adj[k][j]) //이동 가능한 경우
                        adj[i][j] = adj[i][k] + adj[k][j];
                }
            }
        }

        if(0 < adj[0][n + 1] && adj[0][n + 1] < 102) return true;
        else return false;
    }

    //모든 정점과 모든 정점 사이에 50ml 맥주 20개 (1000ml) 로 이동 가능한지 판단
    private static boolean isAvailable(int i, int j) {
        int xDiff = Math.abs(m.get(i).x - m.get(j).x);
        int yDiff = Math.abs(m.get(i).y - m.get(j).y);
        if(xDiff + yDiff <= 1000) return true;
        else return false;
    }
}
