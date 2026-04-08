import java.util.*;

public class RiskThresholdLookup {

    static void linearSearch(List<Integer> list, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            comparisons++;
            if (list.get(i) == target) {
                System.out.println("Linear: threshold=" + target + " found at index " + i + " (" + comparisons + " comps)");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: threshold=" + target + " → not found (" + comparisons + " comps)");
        }
    }

    static void binaryFloorCeil(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        int comparisons = 0;

        Integer floor = null;
        Integer ceil = null;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            if (list.get(mid) == target) {
                floor = list.get(mid);
                ceil = list.get(mid);
                break;
            } else if (list.get(mid) < target) {
                floor = list.get(mid);
                low = mid + 1;
            } else {
                ceil = list.get(mid);
                high = mid - 1;
            }
        }

        System.out.println("Binary floor(" + target + "): " + floor +
                ", ceiling: " + ceil + " (" + comparisons + " comps)");
    }

    static int insertionPoint(List<Integer> list, int target) {
        int low = 0, high = list.size();

        while (low < high) {
            int mid = (low + high) / 2;

            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {

        List<Integer> risks = Arrays.asList(10, 25, 50, 100);

        int target = 30;

        linearSearch(risks, target);

        binaryFloorCeil(risks, target);

        int pos = insertionPoint(risks, target);
        System.out.println("Insertion point for " + target + ": index " + pos);
    }
}