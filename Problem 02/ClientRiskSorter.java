import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

public class ClientRiskSorter {

    public static void bubbleSortAscending(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // Visualization
                    System.out.println("Swap: " + arr[j].name + " <-> " + arr[j + 1].name);
                }
            }

            if (!swapped) break; // Early termination
        }

        System.out.println("Total Swaps: " + swaps);
    }

    public static void insertionSortDescending(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j]; // shift right
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // Comparator: risk DESC, then balance DESC
    private static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    public static List<Client> topNClients(Client[] arr, int n) {
        List<Client> result = new ArrayList<>();
        for (int i = 0; i < Math.min(n, arr.length); i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Bubble Sort (Ascending)
        Client[] bubbleArr = clients.clone();
        bubbleSortAscending(bubbleArr);
        System.out.println("Bubble Sorted: " + Arrays.toString(bubbleArr));

        // Insertion Sort (Descending)
        Client[] insertionArr = clients.clone();
        insertionSortDescending(insertionArr);
        System.out.println("Insertion Sorted (DESC): " + Arrays.toString(insertionArr));

        // Top 3 Risks
        List<Client> topClients = topNClients(insertionArr, 3);
        System.out.println("Top Risks: " + topClients);
    }
}