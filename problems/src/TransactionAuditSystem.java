import java.util.*;

public class TransactionAuditSystem {

    static class Transaction {
        String id;
        double fee;
        String timestamp;

        public Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        public int getTimeValue() {
            String[] parts = timestamp.split(":");
            return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        }

        @Override
        public String toString() {
            return id + ":" + fee;
        }

        public String detailed() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.print("BubbleSort (fees): [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("] // " + passes + " passes, " + swaps + " swaps");
    }

    static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && (
                    list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).getTimeValue() > key.getTimeValue())
            )) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.print("InsertionSort (fee+ts): [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).detailed());
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    static void findOutliers(List<Transaction> list) {
        System.out.print("High-fee outliers: ");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.print(t.id + " ");
                found = true;
            }
        }

        if (!found) System.out.print("none");
        System.out.println();
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        List<Transaction> bubbleList = new ArrayList<>(transactions);
        List<Transaction> insertionList = new ArrayList<>(transactions);

        bubbleSort(bubbleList);
        insertionSort(insertionList);
        findOutliers(transactions);
    }
}