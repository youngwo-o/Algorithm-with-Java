import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1755 {

static class Pair implements Comparable<Pair>{
    int num;
    String alpha;

    public Pair(int num, String alpha) {
        super();
        this.num = num;
        this.alpha = alpha;
    }

    @Override
    public int compareTo(Pair o) {
        for(int i = 0; i < Math.min(this.alpha.length(), o.alpha.length()); ++i) {
            if(this.alpha.charAt(i) < o.alpha.charAt(i))
                return -1;
            else if(this.alpha.charAt(i) > o.alpha.charAt(i))
                return 1;
        }
        if(this.alpha.length() < o.alpha.length()) return -1;					else return 1;
    }

}

    static int M, N;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    private static void solution() {
        List<Pair> numList = new ArrayList<>();
        String[] numAlpha = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i = M; i <= N; ++i) {
            int a = i / 10;
            int b = (i - a * 10);
            String num;
            if(a == 0) num = numAlpha[b];
            else num = numAlpha[a] + numAlpha[b];
            numList.add(new Pair(i, num));
        }

        Collections.sort(numList);

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 0; i < numList.size(); ++i) {
            sb.append(numList.get(i).num + " ");
            cnt++;
            if(cnt == 10) {
                sb.append("\n");
                cnt = 0;
            }
        }

        System.out.println(sb.toString());
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
    }

}
