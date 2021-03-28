import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1647 {

    private static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M;
    static List<Edge> edgeList;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    private static int solution() {
        Collections.sort(edgeList, (a, b) -> a.cost - b.cost);
        makeSet();
        int res = 0, cnt = 0;
        for(Edge e : edgeList) {
            if(union(e.from, e.to)) {
                res += e.cost;
                cnt++;
                if(cnt == N - 2) break; // 정점 N개, 간선 N - 2 개인 경우 2 집합으로 나뉨
            }
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
        for(int i = 1; i <= N; ++i) parent[i] = i;
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for(int i = 0; i < M; ++i) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(from, to, cost));
        }
    }
}
