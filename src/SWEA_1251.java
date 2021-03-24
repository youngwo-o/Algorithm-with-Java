import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1251 {

    private static class Island {
        int x, y;

        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        int node;
        long dist; //int로 하면 overflow

        public Edge(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int N;
    static double E;
    static Island[] island;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for(int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(in.readLine());
            island = new Island[N];

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for(int i = 0; i < N; ++i) {
                int x = Integer.parseInt(st.nextToken());
                island[i] = new Island(x, 0);
            }
            st = new StringTokenizer(in.readLine(), " ");
            for(int i = 0; i < N; ++i) {
                int y = Integer.parseInt(st.nextToken());
                island[i].y = y;
            }

            E = Double.parseDouble(in.readLine());

            System.out.println("#" + tc + " " + Math.round((E * solution())));
        }
    }

    //Prim
    private static long solution() {
        long ans = 0;
        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        pq.add(new Edge(0, 0));

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(visited[now.node]) continue;

            visited[now.node] = true;
            ans += now.dist;

            Island nowI = island[now.node];
            for(int i = 0; i < N; ++i) {
                if(visited[i]) continue;

                Island nextI = island[i];
                long xDiff = (long)(nowI.x - nextI.x);
                long yDiff = (long)(nowI.y - nextI.y);
                long dist = xDiff * xDiff + yDiff * yDiff; //나중에 제곱해줘야하기 때문에 루트 안씌움
                pq.add(new Edge(i, dist));
            }

        }

        return ans;
    }

}
