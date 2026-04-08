import java.util.*;

public class ClientRiskRanking {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore;
        }
    }

    static void bubbleSort(List<Client> list) {
        int n = list.size();
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).riskScore > list.get(j + 1).riskScore) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.print("Bubble (asc): [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("] // Swaps: " + swaps);
    }

    static void insertionSort(List<Client> list) {
        for (int i = 1; i < list.size(); i++) {
            Client key = list.get(i);
            int j = i - 1;

            while (j >= 0 && (
                    list.get(j).riskScore < key.riskScore ||
                            (list.get(j).riskScore == key.riskScore &&
                                    list.get(j).accountBalance < key.accountBalance)
            )) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.print("Insertion (desc): [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    static void topRisks(List<Client> list, int k) {
        System.out.print("Top " + k + " risks: ");
        for (int i = 0; i < Math.min(k, list.size()); i++) {
            Client c = list.get(i);
            System.out.print(c.name + "(" + c.riskScore + ")");
            if (i < k - 1 && i < list.size() - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("clientC", 80, 1000));
        clients.add(new Client("clientA", 20, 5000));
        clients.add(new Client("clientB", 50, 2000));

        List<Client> bubbleList = new ArrayList<>(clients);
        List<Client> insertionList = new ArrayList<>(clients);

        bubbleSort(bubbleList);
        insertionSort(insertionList);
        topRisks(insertionList, 3);
    }
}