import java.util.Arrays;

public class Main {

    // 🔹 Linear Search (first occurrence)
    static int linearFirst(String[] arr, String target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First Index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Not found, Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Linear Search (last occurrence)
    static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear Last Index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Not found, Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Binary Search (any occurrence)
    static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                System.out.println("Binary Found at Index: " + mid + ", Comparisons: " + comparisons);
                return mid;
            }
            else if (arr[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        System.out.println("Not found, Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Count occurrences using Binary Search
    static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, res = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                res = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return res;
    }

    static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, res = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                res = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return res;
    }

    // 🔹 Main
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};
        String target = "accB";

        // Linear Search
        linearFirst(logs, target);
        linearLast(logs, target);

        // Sort before Binary Search
        Arrays.sort(logs);
        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        // Binary Search
        int index = binarySearch(logs, target);

        // Count duplicates
        int count = countOccurrences(logs, target);
        System.out.println("Total Occurrences: " + count);
    }
}