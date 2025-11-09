import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        if (L != null) {
            for (int element: L) {
                sum += element;
            }
            return sum;
        } else {
            return 0;
        }
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> evensList = new ArrayList<>();
        for (int element: L) {
            if (element % 2 == 0) {
                evensList.add(element);
            }
        }
        return evensList;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> commonL = new ArrayList<>();
        for (int element: L1) {
            if (L2.contains(element)) {
                commonL.add(element);
            }
        }
        return commonL;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int sum = 0;
        for (String element: words) {
            if (element.indexOf(c) != -1) {
                sum ++;
            }
        }
        return sum;
    }
}
