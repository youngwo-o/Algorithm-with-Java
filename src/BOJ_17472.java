import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472 {

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, num = 2;
    static int[][] board;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] minCost;
    static List<Edge> edgeList;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input();
        numbering();
        findEdge();
        System.out.println(kruskal());
    }

    private static int kruskal() {
        Collections.sort(edgeList, (a, b) -> a.cost - b.cost);
        makeSet();
        int res = 0;
        for(Edge e : edgeList) {
            //해당 간선을 선택한 경우, 사이클이 생기지 않으면 간선 연결
            if(union(e.from, e.to)) res += e.cost;
        }
        if(res == 0) res = -1;
        int prev = findSet(2);
        //모든 섬이 연결되어 있는지 확인 (parent 값을 확인하고, 모두 같은지 확인)
        for(int i = 3; i < num; ++i) {
            int p = findSet(i);
            if(p != prev) {
                res = -1;
                break;
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
        parent = new int[num];
        for(int i = 2; i < num; ++i) {
            parent[i] = i;
        }
    }

    private static void findEdge() {
        minCost = new int[num][num]; //from에서 to까지의 여러 간선의 가중치 중에 가장 작은 가중치 구하기
        for(int i = 2; i < num; ++i) {
            Arrays.fill(minCost[i], Integer.MAX_VALUE);
        }
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                if(board[i][j] != 0) { //섬에서 다른 섬으로의 가능한 모든 간선 찾기
                    for(int k = 0; k < 4; ++k) { //4방 탐색
                        Edge e = makeEdge(i, j, k);
                        if(e == null) continue;
                        if(e.cost >= 2) minCost[e.from][e.to] = Math.min(e.cost, minCost[e.from][e.to]);
                    }
                }
            }
        }
        //가장 작은 가중치를 갖는 간선의 정보 저장
        edgeList = new ArrayList<>();
        for(int i = 2; i < num; ++i) {
            //(from, to) 와 (to, from) 두가지가 저장되므로 j 는 i + 1 부터
            for(int j = i + 1; j < num; ++j) {
                if(minCost[i][j] != Integer.MAX_VALUE) edgeList.add(new Edge(i, j, minCost[i][j]));
            }
        }

    }

    private static Edge makeEdge(int r, int c, int d) {
        int cnt = -1, idx = board[r][c];
        if(d == 0 || d == 1) { //위, 아래 연결
            while(true) {
                r += dir[d][0];
                if (r < 0 || r >= N || board[r][c] == idx) return null;
                cnt++;
                if (board[r][c] != idx && board[r][c] != 0) return new Edge(idx, board[r][c], cnt);
            }
        }
        else if(d == 2 || d == 3) { //왼쪽, 오른쪽 연결
            while(true) {
                c += dir[d][1];
                if (c < 0 || c >= M || board[r][c] == idx) return null;
                cnt++;
                if (board[r][c] != idx && board[r][c] != 0) return new Edge(idx, board[r][c], cnt);
            }
        }
        return null;
    }

    private static void numbering() {
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                //땅인 경우 섬 번호 붙이기
                if(board[i][j] == 1) setNum(i, j);
            }
        }
    }

    private static void setNum(int r, int c) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(r, c));
        int idx = board[r][c];
        while(!q.isEmpty()) {
            Pair now = q.poll();

            board[now.r][now.c] = num;

            for(int i = 0; i < 4; ++i) {
                int nr = now.r + dir[i][0];
                int nc = now.c + dir[i][1];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if(board[nr][nc] == idx) q.add(new Pair(nr, nc));
            }
        }
        num++;
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
