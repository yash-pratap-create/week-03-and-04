import java.util.*;

public class TradeVolumeAnalysis{

    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    static void mergeSort(List<Trade> list) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<Trade> left = new ArrayList<>(list.subList(0, mid));
        List<Trade> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    static void merge(List<Trade> result, List<Trade> left, List<Trade> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).volume <= right.get(j).volume) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) result.set(k++, left.get(i++));
        while (j < right.size()) result.set(k++, right.get(j++));
    }

    static void quickSort(List<Trade> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    static int partition(List<Trade> list, int low, int high) {
        int pivot = list.get(high).volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).volume > pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    static List<Trade> mergeTwoSorted(List<Trade> a, List<Trade> b) {
        List<Trade> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i).volume <= b.get(j).volume) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }

        while (i < a.size()) result.add(a.get(i++));
        while (j < b.size()) result.add(b.get(j++));

        return result;
    }

    static int totalVolume(List<Trade> list) {
        int sum = 0;
        for (Trade t : list) sum += t.volume;
        return sum;
    }

    public static void main(String[] args) {

        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade("trade3", 500));
        trades.add(new Trade("trade1", 100));
        trades.add(new Trade("trade2", 300));

        List<Trade> mergeList = new ArrayList<>(trades);
        mergeSort(mergeList);

        System.out.print("MergeSort: [");
        for (int i = 0; i < mergeList.size(); i++) {
            System.out.print(mergeList.get(i));
            if (i < mergeList.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        List<Trade> quickList = new ArrayList<>(trades);
        quickSort(quickList, 0, quickList.size() - 1);

        System.out.print("QuickSort (desc): [");
        for (int i = 0; i < quickList.size(); i++) {
            System.out.print(quickList.get(i));
            if (i < quickList.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        List<Trade> merged = mergeTwoSorted(mergeList, mergeList);

        System.out.println("Merged morning+afternoon total: " + totalVolume(trades));
    }
}