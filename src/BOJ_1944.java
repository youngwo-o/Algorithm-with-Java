import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1944 {

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, cnt;
    static int[][] miro;
    static List<Pair> rnk;
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<Edge> edgeList;
    static int[] parent;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        input();
        if(!findAllEdge()) {
            System.out.println("-1");
            return;
        }
        System.out.println(kruskal());
    }

    private static int kruskal() {
        // kruskal 로 모든 정점을 사이클 없이 최소값으로 연결하기
        edgeList.sort((a, b) -> a.cost - b.cost);
        makeSet();
        int res = 0, cnt = 0;
        for(Edge e : edgeList) {
            if(union(e.from, e.to)) {
                res += e.cost;
                cnt++;
            }
        }
        if(cnt != rnk.size() - 1) res = -1; // 모든 정점이 연결되지 않은 경우
        if(res == 0) res = -1; // 연결된 정점이 없는 경우
        return res;
    }

    private static boolean union(int from, int to) {
        int fromP = findSet(from);
        int toP = findSet(to);
        if(fromP == toP) return false;
        parent[toP] = fromP;
        return true;
    }

    private static int findSet(int node) {
        if(node == parent[node]) return node;
        return parent[node] = findSet(parent[node]);
    }

    private static void makeSet() {
        parent = new int[M + 1];
        for(int i = 0 ; i < M + 1; ++i) parent[i] = i;
    }

    private static boolean findAllEdge() {
        edgeList = new ArrayList<>();
        for(int i = 0; i < rnk.size(); ++i) { // S 와 K 사이의 모든 거리 구하기
            findEdge(i);
        }
        return true;
    }

    private static void findEdge(int node) {
        Queue<int[]> q = new ArrayDeque<>();
        visited = new boolean[N][N];

        Pair n = rnk.get(node);

        q.add(new int[]{n.r, n.c, 0});
        visited[n.r][n.c] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            int dist = now[2];

            for(int i = 0; i < 4; ++i) {
                int nr = r + d[i][0];
                int nc = c + d[i][1];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                int val = miro[nr][nc];
                if(val == -1) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, dist + 1});
                }
                else if(val != -2) // S 또는 K 를 찾은 경우, 간선 리스트에 저장
                    edgeList.add(new Edge(node, miro[nr][nc], dist + 1));
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N][N];
        rnk = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            char[] ch = in.readLine().toCharArray();
            for(int j = 0; j < N; ++j) {
                if(ch[j] == '1') miro[i][j] = -2;
                else if(ch[j] == '0') miro[i][j] = -1;
                else if(ch[j] == 'S' || ch[j] =='K') {
                    miro[i][j] = cnt++;
                    rnk.add(new Pair(i, j));
                }
            }
        }
    }
}
