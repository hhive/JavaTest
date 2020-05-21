package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Algorithm {
    public static void main(String[] arg) {
        new DivideTest().getArrange();
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],输出: 6解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     * @return
     */
    public int maxSubArray() {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] dp = new int[n];

        dp[0] = nums[0];

        int result = dp[0];

        for (int i = 1; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
        return result;
    }
}

/**
 *   有1，2，3，4个数，问你有多少种排列方法，并输出排列。
 */
class DivideTest {
    int count = 0;
    int result = 0;
    int [] prepare() {
        return new int[]{1, 2, 3, 4};
    }
    void getArrange() {
        int[] nums = prepare();
        System.out.println(divide(nums));
    }
    int divide(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums.length == 1) {
                result += 1;
                return result;
            }
            int[] temp = new int[nums.length - 1 ];
            int k = 0;
            for (int j = i + 1; j < nums.length; j++) {
                temp[k++] = nums[j];
            }
            divide(temp);
        }
        return result;
    }
}

/**
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 *
 * 注意：
 *
 * 所有花费均为正整数。
 *
 * 示例：
 *
 * 输入: [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
 *      或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
 * 进阶：
 * 您能否在 O(nk) 的时间复杂度下解决此问题？
 */
class DynamicProgrammingTest2 {
    /** 准备参数*/
    int[][] prepare() {
        return new int[][] {
            {1,5,3},
            {2,9,3},
            {6,3,7},
            {7,5,2},
            {5,8,9}
        };
    }

    void getMinimumCost() {
        int[][] cost = prepare();
        int n = cost.length;
        int k = cost[0].length;
        int[][] dp = new int[n][k];
        for (int i = 0; i < k; i++) {
            dp[0][i] = cost[0][i];
        }
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            /** 时间复杂度为O(nk)*/
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (min1 > dp[i -1][j]) {
                    min2 = min1;
                    min1 = dp[i -1][j];
                    minIndex = j;
                } else if (min2 > dp[i - 1][j]){
                    min2 = dp[i - 1][j];
                }
            }

            for (int j = 0; j < k; j++) {
                if (minIndex != j) {
                    dp[i][j] = min1 + cost[i][j];
                } else {
                    dp[i][j] = min2 + cost[i][j];
                }
            }
            /** 时间复杂度为O(nk^2)*/
            // for (int j = 0; j < k; j++) {
            //     dp[i][j] = Integer.MAX_VALUE;
            //     for (int m = 0; m < k; m++) {
            //         if (m != j) {
            //             if (dp[i - 1][m] < dp[i][j]) {
            //                 dp[i][j] = dp[i - 1][m];
            //             }
            //         }
            //     }
            //     dp[i][j] += cost[i][j];
            // }
        }
        int result = dp[n - 1][0];
        for (int i = 1; i < k; i++) {
            if (dp[n - 1][i] < result) {
                result = dp[n - 1][i];
            }
        }
        System.out.println(result);
    }
}

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * 策略： 算出某一层任意位置到下一层的最短路径，保存后用于上一层计算到这层的最短路径
 */
class  DynamicProgrammingTest1{
    /** 准备参数*/
    List<List<Integer>> prepare() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        row.add(2);
        triangle.add(row);
        row = Arrays.asList(3, 4);
        triangle.add(row);
        row = Arrays.asList(6, 5, 7);
        triangle.add(row);
        row = Arrays.asList(4, 7, 8, 3);
        triangle.add(row);
        row = Arrays.asList(7, 6, 9, 2, 1);
        triangle.add(row);
        row = Arrays.asList(1, 2, 8, 9, 9, 8);
        triangle.add(row);
        System.out.println(triangle);
        return triangle;
    }

    void getShortestPath() {
        List<List<Integer>> triangle = prepare();
        int n = triangle.size();
        int[][] dp = new int[n][n];
        List<Integer> lastRow = triangle.get(n - 1);
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = lastRow.get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < i + 1; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + row.get(j);
            }
        }
        System.out.println("最短路径为：" + dp[0][0]);
    }
}


/**
 *失去注释，无法解释
 */
class MatrixChain {
    int m[][] = new int[6][6];
    int s[][] = new int[6][6];
    int p[] = {30,35,15,5,10,20};
    int n = 5;

    void print() {
        matrixChain();
        System.out.println(m[1][5]);
        System.out.println(s[1][5]);
    }
    void matrixChain() {
        System.out.println(n);
        int r,i,j,k,t;
        for (r = 2; r <= n; r++){
            for(i = 1; i <= n-r+1; i++) {
                j = i + r -1;
                m[i][j] = m[i+1][j] + p[i -1] * p[i] * p[j];
                s[i][j] = i;
                for (k = i + 1; k <= j -1; k++) {
                    t = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }
}
