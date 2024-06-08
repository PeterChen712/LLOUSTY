package llousty.Utils;

public class SearchEngine {
    public static boolean containsSearchName(String target, String search) {
        target = target.toLowerCase();
        search = search.toLowerCase();
        String pattern = "\\b" + search + "\\b";
        return target.matches(".*" + pattern + ".*");
    }
}
