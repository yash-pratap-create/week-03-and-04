import java.util.*;

public class AccountIdLookup {

    static int linearFirst(List<String> logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.size(); i++) {
            comparisons++;
            if (logs.get(i).equals(target)) {
                System.out.println("Linear first " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear first " + target + ": not found (" + comparisons + " comparisons)");
        return -1;
    }

    static int linearLast(List<String> logs, String target) {
        int comparisons = 0;
        int index = -1;

        for (int i = 0; i < logs.size(); i++) {
            comparisons++;
            if (logs.get(i).equals(target)) {
                index = i;
            }
        }

        if (index != -1)
            System.out.println("Linear last " + target + ": index " + index + " (" + comparisons + " comparisons)");
        else
            System.out.println("Linear last " + target + ": not found (" + comparisons + " comparisons)");

        return index;
    }

    static int binarySearch(List<String> logs, String target) {
        int low = 0, high = logs.size() - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            int cmp = logs.get(mid).compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary " + target + ": index " + mid + " (" + comparisons + " comparisons)");
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary " + target + ": not found (" + comparisons + " comparisons)");
        return -1;
    }

    static int countOccurrences(List<String> logs, String target, int index) {
        if (index == -1) return 0;

        int count = 1;

        int i = index - 1;
        while (i >= 0 && logs.get(i).equals(target)) {
            count++;
            i--;
        }

        i = index + 1;
        while (i < logs.size() && logs.get(i).equals(target)) {
            count++;
            i++;
        }

        return count;
    }

    public static void main(String[] args) {

        List<String> logs = new ArrayList<>();
        logs.add("accB");
        logs.add("accA");
        logs.add("accB");
        logs.add("accC");

        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        Collections.sort(logs);

        System.out.println("\nSorted logs: " + logs);

        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB", index);

        System.out.println("Count of accB: " + count);
    }
}