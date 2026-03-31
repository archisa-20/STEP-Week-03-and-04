

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
}

public class Main {

    // 🔹 Merge Sort (ASC by returnRate, stable)
    static void mergeSort(Asset[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge(Asset[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) // stable
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 🔹 Quick Sort (DESC returnRate, ASC volatility)
    static void quickSort(Asset[] arr, int low, int high) {
        if (high - low < 10) { // Hybrid: use insertion sort
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot)) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // 🔹 Comparison logic
    static boolean compare(Asset a, Asset b) {
        if (a.returnRate > b.returnRate) return true;
        if (a.returnRate == b.returnRate)
            return a.volatility < b.volatility;
        return false;
    }

    // 🔹 Median-of-3 pivot
    static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate > arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate > arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate > arr[high].returnRate) swap(arr, mid, high);

        return mid;
    }

    // 🔹 Insertion Sort (for small partitions)
    static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && !compare(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 🔹 Swap helper
    static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 🔹 Print helper
    static void printAssets(Asset[] arr) {
        for (Asset a : arr)
            System.out.print(a.name + ":" + a.returnRate + "% ");
        System.out.println();
    }

    // 🔹 Main
    public static void main(String[] args) {

        Asset[] assets = {
            new Asset("AAPL", 12, 5),
            new Asset("TSLA", 8, 7),
            new Asset("GOOG", 15, 4)
        };

        // Merge Sort (ASC)
        mergeSort(assets, 0, assets.length - 1);
        System.out.print("Merge Sort (ASC): ");
        printAssets(assets);

        // Quick Sort (DESC + volatility ASC)
        quickSort(assets, 0, assets.length - 1);
        System.out.print("Quick Sort (DESC): ");
        printAssets(assets);
    }
}