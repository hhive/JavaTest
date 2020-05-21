package algorithm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/12/4 21:23
 */
public class Algorithm {

    private static Logger log = Logger.getLogger(Algorithm.class);

    public static void main(String[] args) {
        // new Algorithm().greedy();
        System.out.println(new Algorithm().dynamicPrograming(9, 1));
    }

    /**
     * 贪心算法测试
     */
    void greedy() {
        /** 需要支付的总值 */
        int amount = 456;
        /** Map<币值，张数> */
        Map<Integer, Integer> money = new HashMap<>();
        money.put(1, 5);
        money.put(5, 2);
        money.put(10, 2);
        money.put(50, 3);
        money.put(100, 5);
        /** 使用贪心算法算出在money设定的币值和张数下支付amount需要的最少张数 */
        int[] moneyValues = new int[money.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : money.entrySet()) {
            moneyValues[i++] = entry.getKey();
        }
        /** 对币值按从大到小的顺序进行排序 */
        for (int j = 0; j < moneyValues.length; j++) {
            int max = j;
            for (int k = j + 1; k < moneyValues.length; k++) {
                if (moneyValues[k] > moneyValues[max]) {
                    max = k;
                }
            }
            int temp = moneyValues[j];
            moneyValues[j] = moneyValues[max];
            moneyValues[max] = temp;
        }
        Map<Integer, Integer> moneyResults = new HashMap<>();
        int number = 0;
        int leftNumber = 0;
        int allNumber = 0;
        for (int moneyValue : moneyValues) {
            leftNumber = money.get(moneyValue);
            while (amount >= moneyValue && leftNumber > 0) {
                money.replace(moneyValue, --leftNumber);
                number++;
                amount -= moneyValue;
            }
            moneyResults.put(moneyValue, number);
            allNumber += number;
            number = 0;
        }
        log.info("amount: " + amount);
        log.info(moneyResults);
        log.info("allNumber: " + allNumber);
    }

    /**
     * 动态规划测试
     *
     * @param n 需要计算的次数
     * @param i 参与计算的数
     */
    int dynamicPrograming(int n, int i) {
        int result = 0;
        if (n > 0) {
            result = dynamicPrograming(n - 1, i) + i;
        }
        return result;
    }
}
