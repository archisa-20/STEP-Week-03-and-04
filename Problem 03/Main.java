class Trade {
    int id, volume;

    Trade(int id, int volume) {
        this.id = id;
        this.volume = volume;
    }
}

public class Main {

    // 🔹 Merge Sort (ASC, Stable)
    static void mergeSort(Trade[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge(Trade[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) // stable
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 🔹 Quick Sort (DESC)
    static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) { // DESC
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // 🔹 Merge Two Sorted Lists
    static Trade[] mergeTwoLists(Trade[] a, Trade[] b) {
        int i = 0, j = 0, k = 0;
        Trade[] result = new Trade[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume)
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // 🔹 Total Volume
    static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr)
            sum += t.volume;
        return sum;
    }

    // 🔹 Print Helper
    static void printTrades(Trade[] arr) {
        for (Trade t : arr)
            System.out.print(t.id + ":" + t.volume + " ");
        System.out.println();
    }

    // 🔹 Main
    public static void main(String[] args) {

        Trade[] trades = {
            new Trade(3, 500),
            new Trade(1, 100),
            new Trade(2, 300)
        };

        // Merge Sort (ASC)
        mergeSort(trades, 0, trades.length - 1);
        System.out.print("Merge Sort (ASC): ");
        printTrades(trades);

        // Quick Sort (DESC)
        quickSort(trades, 0, trades.length - 1);
        System.out.print("Quick Sort (DESC): ");
        printTrades(trades);

        // Example: Merge two sorted lists
        Trade[] morning = {
            new Trade(1, 100),
            new Trade(2, 300)
        };

        Trade[] afternoon = {
            new Trade(3, 500)
        };

        Trade[] merged = mergeTwoLists(morning, afternoon);
        System.out.print("Merged List: ");
        printTrades(merged);

        // Total Volume
        int total = totalVolume(merged);
        System.out.println("Total Volume: " + total);
    }
}