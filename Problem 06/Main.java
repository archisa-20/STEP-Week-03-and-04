import java.util.Arrays;

public class Main {

    // 🔹 Linear Search (unsorted)
    static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Found at index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Linear: Not found, Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Binary Search (insertion point / lower bound)
    static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }

        System.out.println("Lower Bound Index (insertion point): " + low + ", Comparisons: " + comparisons);
        return low;
    }

    // 🔹 Floor (largest ≤ target)
    static int floorValue(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Floor: " + arr[mid] + ", Comparisons: " + comparisons);
                return arr[mid];
            }
            else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Floor: " + floor + ", Comparisons: " + comparisons);
        return floor;
    }

    // 🔹 Ceiling (smallest ≥ target)
    static int ceilingValue(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Ceiling: " + arr[mid] + ", Comparisons: " + comparisons);
                return arr[mid];
            }
            else if (arr[mid] > target) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println("Ceiling: " + ceil + ", Comparisons: " + comparisons);
        return ceil;
    }

    // 🔹 Main
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // Linear Search (unsorted example)
        int[] unsorted = {50, 10, 100, 25};
        linearSearch(unsorted, target);

        // Sort for Binary Search
        Arrays.sort(risks);
        System.out.println("Sorted Risks: " + Arrays.toString(risks));

        // Lower Bound (insertion point)
        int index = lowerBound(risks, target);

        // Floor & Ceiling
        int floor = floorValue(risks, target);
        int ceil = ceilingValue(risks, target);
    }
}