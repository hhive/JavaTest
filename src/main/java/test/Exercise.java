package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Exercise {
    /**
     * @param arg
     */
    public static void main(String[] arg) {

            new ZhiYinShu().test();
//        String info = "Java1";
//        System.out.println( new CompareChar().encrypt(info));

//        new Max().test();
//        new StringCatcher().print();
    }
}

/**
 * 质因数分解
 */
class ZhiYinShu {
    public void test() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        ArrayList<Integer> yinZi = new ArrayList<>();
        int half = num / 2;
        System.out.println(half);
        for (int i = 2; i <= half; i++) {
            if (num % i == 0) {
                yinZi.add(i);
                num = num / i;
                i = 1;
            }
        }
        System.out.println(yinZi.toString());
    }
}

/**
 *
 */
class DiaoDu {
    public void test() {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();
        int M = sc.nextInt();
        int[] p = new int[P];
        for (int i = 0; i < P; i++) {
            p[i] = sc.nextInt();
        }
        int[] m = new int[M];
        for (int i = 0; i < P; i++) {
            p[i] = sc.nextInt();
        }
        int[][] FLOPS = new int[P][M];
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < M; j++) {
                FLOPS[i][j] = sc.nextInt();
            }
        }
        int[] lie = new int[M];
        int[] Max = new int[P];
        for (int i = 0; i < P; i++) {
            int max = 0;
            for (int j = 0; j < M - 1; j++) {
                if (FLOPS[i][j + 1] > FLOPS[i][max] && notLie(lie,j)) {
                    max = j +1;
                }
                Max[i] = max;
            }
        }
    }
    public boolean notLie(int[] lie,int j) {
        return false;
    }
}

/**
 * 宝藏在M米，甲乙每次挖A，B米，谁先挖到
 */
class BaoZang {
    public void test() {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int size = sc.nextInt();
        int flagA = 1;
        int flagB = -1;
        int[] des = new int[size];
        for (int i = 0; i < size; i++) {
                des[i] = sc.nextInt();
        }
        int get = 0;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            while (true) {
                get += A;
                if (get == des[i]) {
                    res[i] = flagA;
                    get = 0;
                    break;
                }
                get += B;
                if (get == des[i]) {
                    get = 0;
                    res[i] = flagB;
                    break;
                }
            }
        }
        for (int x : res) {
            System.out.print(x + " ");
        }
//        for (int i = 0; i < size; i++) {
//            if (i == size - 1) {
//                System.out.print(res[i]);
//            } else {
//                System.out.print(res[i] + " ");
//            }
//        }
    }
}
/**
 *
 * M{,N}.对称对总排列
 */

/**
 *有 n 个学生站成一排，每个学生有一个能力值，
 * 牛牛想从这 n 个学生中按照顺序选取 k 名学生，
 * 要求相邻两个学生的位置编号的差不超过 d，使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
 */
class Max {
    ArrayList<Integer> student = new ArrayList<>();
    public void test() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            //总人数
            int n = sc.nextInt();
            //学生能力值数组，第i个人直接对应arr[i]
            int[] arr = new int[n + 1];
            //初始化
            for (int i = 1; i <= n; i++) {//人直接对应坐标
                arr[i] = sc.nextInt();
            }
            //选择的学生数
            int kk = sc.nextInt();
            //间距
            int dd = sc.nextInt();

            /**
             * 递推的时候，以f[one][k]的形式表示
             * 其中：one表示最后一个人的位置，k为包括这个人，一共有k个人
             * 原问题和子问题的关系：f[one][k]=max{f[left][k-1]*arr[one],g[left][k-1]*arr[one]}
             */
            //规划数组
            long[][] f = new long[n + 1][kk + 1];//人直接对应坐标,n和kk都要+1
            long[][] g = new long[n + 1][kk + 1];
            //初始化k=1的情况
            for(int one = 1;one<=n;one++){
                f[one][1] = arr[one];
                g[one][1] = arr[one];
            }
            //自底向上递推
            for(int k=2;k<=kk;k++) {
                for(int one = k;one<=n;one++) {
                    //求解当one和k定的时候，最大的分割点
                    long tempmax = Long.MIN_VALUE;
                    long tempmin = Long.MAX_VALUE;
                    for(int left = Math.max(k-1,one-dd);left<=one-1;left++){
                        if(tempmax<Math.max(f[left][k-1]*arr[one],g[left][k-1]*arr[one])){
                            tempmax=Math.max(f[left][k-1]*arr[one],g[left][k-1]*arr[one]);
                        }
                        if(tempmin>Math.min(f[left][k-1]*arr[one],g[left][k-1]*arr[one])){
                            tempmin=Math.min(f[left][k-1]*arr[one],g[left][k-1]*arr[one]);
                        }
                    }
                    f[one][k] = tempmax;
                    g[one][k] = tempmin;
                }
            }
            //n选k最大的需要从最后一个最大的位置选
            long result = Long.MIN_VALUE;
            for(int one = kk;one<=n;one++){
                if(result<f[one][kk]){
                    result = f[one][kk];
                }
            }
            System.out.println(result);
        }
    }
}

/**
 * 从第一个元素开始，按照元素的大小移动，
 * 正数后移，负数前移，0不移，看是否能越界，能true
 */
class YueJie {
    /**
     *
     */
    public void test() {
        System.out.println("请输入数组的大小：");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = sc.nextInt();
        }
        try {
            for (int i = 0; i < size; ) {
                i += a[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("true");
        }
    }
}

/**
 *
 */
class Kills {
    int i;
    List<Integer> kills = new ArrayList<>();
    List<Integer> store = new ArrayList<>();

    /**
     *
     */
    public void kills() {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            kills.add(sc.nextInt());
        }
        System.out.println(kills);
        int count = 0;
        for (int i = 0; ; i = 0) {
            while (kills.size() > i + 1) {
                if (kills.get(i) > kills.get(i + 1)) {
                    store.add(kills.get(i + 1));
                    i++;
                } else {
                    i++;
                }

            }
            i = 0;
            if (store.size() == 0) {
                break;
            }
            while (store.size() > i) {
                //remove(int position) and remove(object object)
                kills.remove(store.get(i));
                i++;
            }
            store.clear();
            count++;
        }
        System.out.println(count);
    }

}

/**
 *
 */
class GaiFeng {
    /**
     * @param list
     * @return
     */
    private static double averageOfList(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum / (double) list.size();
    }

    /**
     *
     */
    public void gaiFeng() {
        int ave = 0;
        int index = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int num = Integer.parseInt(input);
        while (num > 0) {
            int count = 0;
            input = sc.nextLine();
            String[] inputs = input.split(" ");
            int totalNum = Integer.parseInt(inputs[0]);
            int requestAve = Integer.parseInt(inputs[1]);
            int[] scores = new int[totalNum];
            input = sc.nextLine();
            inputs = input.split(" ");
            for (int i = 0; i < totalNum; i++) {
                scores[i] = Integer.parseInt(inputs[i]);
            }
            Arrays.sort(scores);
            for (int x : scores) {
                System.out.println(x);
            }
//            for (int i = 0;i < totalNum; i++){
//                int max = 0;
//                for (int j = 1; j< totalNum - i; j++) {
//                    if (scores[j] > scores[max]) {
//                        max = j;
//                    }
//                }
//                if (scores[max] != scores[totalNum - 1 - i]) {
//                    int tmp = scores[max];
//                    scores[max] =  scores[totalNum - 1 - i];
//                    scores[totalNum - 1 - i] = tmp;
//                }
//            }
            while (true) {
                int totalScore = 0;
                for (int x : scores) {
                    totalScore += x;
                }
                ave = totalScore / totalNum;
                System.out.println(ave);
                if (ave < requestAve) {
                    scores[index++] = 100;
                    count++;
                } else {
                    break;
                }
            }
            System.out.println(count);
            num--;
        }
    }

    /**
     * from dalao
     */
    public void gaiFeng2() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        System.out.println(t);
        ArrayList<Integer> resList = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            int count = 0;
            int n = in.nextInt();
            System.out.println(n);
            int X = in.nextInt();
            System.out.println(X);
            ArrayList<Integer> list = new ArrayList<>();

            for (int k = 0; k < n; k++) {
                list.add(in.nextInt());
            }
            Collections.sort(list);

            // 都成立的情况下
            if (averageOfList(list) >= X) {
                resList.add(count);
                continue;
            }

            // 不成立：开始尝试调整每个list
            for (int c = 0; c < list.size(); c++) {
                list.set(c, 100);
                count++;
                if (averageOfList(list) >= X) {
                    resList.add(count);
                    break;
                }
            }
        }
        if (resList.size() == 0) {
            return;
        }

        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i));
        }
    }
}

/**
 *
 */
class QuJian {
    private int n, k, t;
    private int chongfu = 0;
    private int count = 0;

    /**
     *
     */
    public void myQuJian() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] ss = s.split(" ");
        n = Integer.parseInt(ss[0]);
        k = Integer.parseInt(ss[1]);
        t = Integer.parseInt(ss[2]);
        int[] num = new int[n];
        s = sc.nextLine();
        ss = s.split(" ");
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(ss[i]);
        }
        for (int i = 0; i < n - k + 1; i++) {
            for (int j = i; j <= k - 1 + i; j++) {
                int tem = num[j];
                for (int z = j + 1; z <= k - 1 + i; z++) {
                    if (tem == num[z]) {
                        chongfu++;
                    }
                }
                if (chongfu >= t)
                    count++;
            }
        }
        System.out.println(count);
    }
}

/**
 * java 的链表 ；
 */
class BianLiWuXiangTu {
    /**
     *
     */
    public void my() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = br.readLine();
            int num = Integer.parseInt(s);
            String[] ss1 = new String[(num - 1) * 2];
            while (num > 1) {
                s = br.readLine();
                ss1 = s.split(" ");
                num--;
            }
            for (String x : ss1) {
                System.out.println(x);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

/**
 * cross
 */
class Cross {
    /**
     *
     */
    public void cross() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
//            Scanner sc = new Scanner(System.in);
//            String s = sc.nextLine();
//            String[] ss = s.split(" ");
            String s1 = br.readLine();
            String[] ss1 = s1.split(" ");
//            for (String x : ss) {
//                System.out.println(x);
//            }
            for (String x : ss1) {
                System.out.println(x);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

