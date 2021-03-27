import java.util.Locale;

public class Programmers_신규_아이디_추천 {

    class Solution {
        public String solution(String new_id) {
            String id = new_id.toLowerCase();
            id = id.replaceAll("[^a-z0-9-_.]", "");
            id = id.replaceAll("[.]{2,}", ".");
            id = id.replaceAll("^[.]|[.]$","");
            if(id.isEmpty()) id = "a";
            if(id.length() >= 16) {
                id = id.substring(0, 15);
                id = id.replaceAll("[.]$", "");
            }
            while(id.length() < 3) {
                id += id.charAt(id.length() - 1);
            }
            return id;
        }
    }

}
