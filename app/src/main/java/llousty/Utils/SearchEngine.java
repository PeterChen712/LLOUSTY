package llousty.Utils;

public class SearchEngine {
    public static boolean containsSearchName(String target, String search) {
        target = target.toLowerCase();
        search = search.toLowerCase();
    
        String[] words = target.split("\\s+");
    
        for (String word : words) {
            if (word.equals(search)) {
                return true;
            }
        }
        return false;
    }
}
