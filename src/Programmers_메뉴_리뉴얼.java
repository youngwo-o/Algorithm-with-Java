import java.util.*;

public class Programmers_메뉴_리뉴얼 {

    class Solution {

        HashMap<String, Integer> menuList;
        List<String> tmpAns;

        public String[] solution(String[] orders, int[] course) {
            menuList = new HashMap<>();
            tmpAns = new ArrayList<>();

            for (int num : course) {
                for (String o : orders) {
                    char[] tmp = o.toCharArray();
                    Arrays.sort(tmp); // 손님이 주문한 단품메뉴 조합을 오름차순으로 정렬 -> 메뉴 조합에 대한 중복 제거하기 위해
                    for (int i = 0; i < tmp.length; ++i) { //가능한 모든 조합 찾기
                        select(i, tmp[i] + "", tmp, num);
                    }
                }
                //가장 많이 주문한 조합 찾기
                List<String> res = new ArrayList<>();
                int maxVal = 0;
                for (Map.Entry<String, Integer> elem : menuList.entrySet()) {
                    String key = elem.getKey();
                    int val = elem.getValue();

                    if (val >= 2 && val > maxVal) {
                        maxVal = elem.getValue();
                        res.clear();
                        res.add(key);
                    } else if (val >= 2 && elem.getValue() == maxVal)
                        res.add(key);
                }

                tmpAns.addAll(res);
                menuList.clear();
            }

            String[] answer = new String[tmpAns.size()];
            Collections.sort(tmpAns);
            for (int i = 0; i < answer.length; ++i) {
                answer[i] = tmpAns.get(i);
            }
            return answer;
        }

        private void select(int idx, String s, char[] tmp, int num) {
            if (s.length() == num) {
                if (menuList.containsKey(s)) menuList.replace(s, menuList.get(s) + 1);
                else menuList.put(s, 1);
                return;
            }

            for (int i = idx + 1; i < tmp.length; ++i) {
                select(i, s + tmp[i], tmp, num);
            }
        }
    }
}