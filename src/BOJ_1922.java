import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1922 {

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M;
    static List<Edge> adj;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(kruskal());
    }

    private static int kruskal() {
        Collections.sort(adj, (a, b) -> a.cost - b.cost);
        makeSet();
        int res = 0;
        for(Edge e : adj) {
            if(union(e.from, e.to)) res += e.cost;
        }
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
        parent = new int[N + 1];
        for(int i = 1; i <= N; ++i) {
            parent[i] = i;
        }
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        adj = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < M; ++i) {
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.add(new Edge(a, b, c));
        }
    }
}
