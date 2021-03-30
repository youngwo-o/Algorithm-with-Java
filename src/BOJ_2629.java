import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2629 {

    static int N, M;
    static int[] c, gs;
    static boolean[] isSelected;
    static boolean[] ans;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    private static void solution() {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            int num = c[i];
            int resSize = res.size();
            for(int j = 0; j < resSize; ++j) {
                int prev = res.get(j);
                int sum = prev + num;
                int diff = Math.abs(prev - num);

                if(!isSelected[sum]) {
                    res.add(sum);
                    isSelected[sum] = true;
                }
                if(!isSelected[diff]) {
                    res.add(diff);
                    isSelected[diff] = true;
                }
            }
            if(!isSelected[num]) {
                res.add(num);
                isSelected[num] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< M; ++i) {
            if(isSelected[gs[i]]) sb.append("Y ");
            else sb.append("N ");
        }

        System.out.println(sb.toString());
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        c = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        gs = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; ++i) {
            gs[i] = Integer.parseInt(st.nextToken());
        }
        isSelected = new boolean[40001];
    }

}

