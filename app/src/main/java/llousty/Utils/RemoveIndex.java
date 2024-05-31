package llousty.Utils;

import java.util.List;

import llousty.Models.Product;

public class RemoveIndex {

    public static List<String> removeOne(List<String> list, String elementToRemove) {
        int indexToRemove = list.indexOf(elementToRemove);
        if (indexToRemove != -1) {
            list.remove(indexToRemove);
        }

        return list;
    }

    public static List<Double> removeOne(List<Double> list, Double elementToRemove) {
        int indexToRemove = list.indexOf(elementToRemove);
    
        if (indexToRemove != -1) {
            list.remove(indexToRemove);
        }
    
        return list;
    }

    public static List<Product> removeOne(List<Product> list, Product elementToRemove) {
    int indexToRemove = list.indexOf(elementToRemove);

    if (indexToRemove != -1) {
        list.remove(indexToRemove);
    }

    return list;
    }
}