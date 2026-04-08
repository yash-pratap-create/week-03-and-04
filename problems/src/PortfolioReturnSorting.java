import java.util.*;

public class PortfolioReturnSorting {

    static class Asset {
        String name;
        double returnRate;
        double volatility;

        public Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return name + ":" + returnRate + "%";
        }
    }

    static void mergeSort(List<Asset> list) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<Asset> left = new ArrayList<>(list.subList(0, mid));
        List<Asset> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    static void merge(List<Asset> result, List<Asset> left, List<Asset> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).returnRate <= right.get(j).returnRate) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) result.set(k++, left.get(i++));
        while (j < right.size()) result.set(k++, right.get(j++));
    }

    static void quickSort(List<Asset> list, int low, int high) {
        if (high - low <= 10) {
            insertionSort(list, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = medianOf3(list, low, high);
            Collections.swap(list, pivotIndex, high);

            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    static int medianOf3(List<Asset> list, int low, int high) {
        int mid = (low + high) / 2;

        if (list.get(low).returnRate > list.get(mid).returnRate)
            Collections.swap(list, low, mid);
        if (list.get(low).returnRate > list.get(high).returnRate)
            Collections.swap(list, low, high);
        if (list.get(mid).returnRate > list.get(high).returnRate)
            Collections.swap(list, mid, high);

        return mid;
    }

    static int partition(List<Asset> list, int low, int high) {
        Asset pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (
                    list.get(j).returnRate > pivot.returnRate ||
                            (list.get(j).returnRate == pivot.returnRate &&
                                    list.get(j).volatility < pivot.volatility)
            ) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    static void insertionSort(List<Asset> list, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = list.get(i);
            int j = i - 1;

            while (j >= low && (
                    list.get(j).returnRate < key.returnRate ||
                            (list.get(j).returnRate == key.returnRate &&
                                    list.get(j).volatility > key.volatility)
            )) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    static void printList(String label, List<Asset> list) {
        System.out.print(label + ": [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        List<Asset> assets = new ArrayList<>();
        assets.add(new Asset("AAPL", 12, 0.3));
        assets.add(new Asset("TSLA", 8, 0.6));
        assets.add(new Asset("GOOG", 15, 0.2));

        List<Asset> mergeList = new ArrayList<>(assets);
        mergeSort(mergeList);
        printList("Merge", mergeList);

        List<Asset> quickList = new ArrayList<>(assets);
        quickSort(quickList, 0, quickList.size() - 1);
        printList("Quick (desc)", quickList);
    }
}