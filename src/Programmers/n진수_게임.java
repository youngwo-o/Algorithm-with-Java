package Programmers;

class n진수_게임 {

    public String solution(int n, int t, int m, int p) {
        int num = 0, cnt = 0, now = 0;
        StringBuilder sb = new StringBuilder();
        while(sb.length() != t) {
            String res = change(num++, n);
            for(int i = 0; i < res.length(); ++i) {
                if(now++ % m == p - 1) sb.append(res.charAt(i));
                if(sb.length() == t) break;
            }
        }

        return sb.toString();
    }

    public String change(int num, int n) {
        StringBuilder sb = new StringBuilder();
        char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        if(num == 0) return "0";
        while(num != 0) {
            int p = num % n;
            sb.append(d[p]);
            num /= n;
        }

        sb.reverse();

        return sb.toString();
    }
}