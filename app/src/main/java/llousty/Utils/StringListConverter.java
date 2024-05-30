package llousty.Utils;

import java.util.ArrayList;
import java.util.List;

public class StringListConverter {

    private static final String separator = ",";

    public static String stringToList(String string) {
        // Mengonversi string menjadi list
        if (string == null || string.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String[] items = string.split(separator);
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i].trim());
            if (i < items.length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String listToString(List<String> list) {
        // Mengonversi list menjadi string
        if (list == null || list.isEmpty()) {
            return "";
        }
    
        StringBuilder sb = new StringBuilder();
        String separator = ", "; // Tentukan pemisah yang diinginkan
    
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
    
        return sb.toString();
    }

    // public static List<Integer> stringToListInt(String string) {
    //     List<Integer> list = new ArrayList<>();

    //     if (string == null || string.isEmpty()) {
    //         return list;
    //     }

    //     String[] items = string.split(",");
    //     for (String item : items) {
    //         System.out.println("cek " + item);
    //         try {
    //             int value = Integer.parseInt(item.trim());
    //             list.add(value);
    //         } catch (NumberFormatException e) {
    //             // Mengabaikan nilai yang tidak valid
    //         }
    //     }

    //     return list;
    // }


    public static List<Integer> stringToListInt(String string) {
        List<Integer> list = new ArrayList<>();
    
        if (string == null || string.isEmpty()) {
            return list;
        }
    
        String[] items = string.replaceAll("[\\[\\]\\s]", "").split(",");
    
        for (String item : items) {
    
            if (item.isEmpty()) {
                continue; // Mengabaikan item kosong
            }
    
            try {
                int value;
                if (item.startsWith("[")) {
                    value = Integer.parseInt(item.substring(1));
                } else if (item.endsWith("]")) {
                    value = Integer.parseInt(item.substring(0, item.length() - 1));
                } else {
                    value = Integer.parseInt(item.trim());
                }
                list.add(value);
            } catch (NumberFormatException e) {
                // Mengabaikan nilai yang tidak valid
            }
        }
    
        return list;
    }
    

    public static String listIntToString(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String separator = ",";

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }
}