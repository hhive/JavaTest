package test;

/**
 *
 */
public class SearchAndSort {
    /**
     * @param arg
     */
    public static void main(String[] arg) {

        int[] number = {38, 49, 65, 97, 76, 13, 27, 49};
        //new QuickSort(number);
        //new InsertSort().halfInsertSort(number);
        new ShellSort(number);
        for (int x : number) {
            System.out.print(x + " ");
        }
    }
}

/**
 *
 */
class ShellSort {
    /**
     *
     */
    ShellSort(int[] number) {
        shellSort(number);
    }

    /**
     * @param number
     */
    public void shellSort(int[] number) {
//        int j;
        int temp = 0;
        for (int increment = number.length / 2; increment > 0; increment /= 2) {
            for (int i = increment; i < number.length; i++) {
                temp = number[i];
//                for (j = i; j >= increment; j -= increment) {
//                    if (number[j] < number[j - increment]) {
//                        number[j] = number[j - increment];
//                    } else {
//                        break;
//                    }
//                }
//                number[j] = temp;
                if (number[i] < number[i - increment]) {
                    number[i] = number[i - increment];
                    number[i - increment] = temp;
                }
            }
        }
    }
}

/**
 *
 */
class InsertSort {
    /**
     * 插入排序
     * <p>
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     *
     * @param numbers 待排序数组
     */
    public void insertSort(int[] numbers) {
        int size = numbers.length;
        int temp = 0;
        int j = 0;

        for (int i = 0; i < size; i++) {
            temp = numbers[i];
            //假如temp比前面的值小，则将前面的值后移
            for (j = i; j > 0 && temp < numbers[j - 1]; j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = temp;
        }
    }

    /**
     * @param numbers
     */
    public void halfInsertSort(int[] numbers) {
        int size = numbers.length;
        int temp = 0;
        int j = 0;


        for (int i = 1; i < size; i++) {
            temp = numbers[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int m = (low + high) / 2;
                if (temp < numbers[m]) {
                    high = m - 1;
                } else {
                    low = m + 1;
                }
            }
            //假如temp比前面的值小，则将前面的值后移
            for (j = i - 1; j >= high + 1; j--) {
                numbers[j + 1] = numbers[j];
            }
            numbers[high + 1] = temp;
        }
    }
}

/**
 *
 */
class QuickSort {

    /**
     *
     */
    QuickSort(int[] number) {
        recursion(number, 0, number.length - 1);
    }

    /**
     * Find the position that the hub should be in an ordered array
     * and roughly sort the two sides]]
     *
     * @param number
     * @param high
     * @param low
     */
    public int getMiddle(int[] number, int low, int high) {
        int temp = number[low];
        while (low < high) {
            while (low < high && number[high] >= temp) {
                high--;
            }
            //Switch small to low end
            number[low] = number[high];
            while (low < high && number[low] <= temp) {
                low++;
            }
            //Switch big to high end
            number[high] = number[low];
        }
        number[low] = temp;
        return low;
    }

    /**
     * Calling quick sort again on both sides of the hub
     *
     * @param number
     * @param low
     * @param high
     */
    public void recursion(int[] number, int low, int high) {
        if (low < high) {
            int middle = getMiddle(number, low, high);
            recursion(number, low, middle - 1);
            recursion(number, middle + 1, high);
        }
    }
}
