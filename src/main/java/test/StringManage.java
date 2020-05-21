package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringManage {

    /**
     *
     */
    public static void main(String[] arg) {

        //new ReferencePass().test();
        //new StringTest().test();
//        new MatchString().matchString();
        new StringBuilderTest().test();
    }
}

class StringBuilderTest {
    public void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("aa");
        sb.append("bb");
        sb = new StringBuilder("cc");
        sb.append("dd");
        System.out.println(sb);
    }
}

/**
 * String的引用传递
 * String是不可变类型，所以s的值不变，新生成了内存空间保存新值，
 * 而StringBuilder的值直接被改变了
 */
class ReferencePass {

    public static String appStr(String s){
        s += "bbb";
        return s;
    }

    public static StringBuilder appSb(StringBuilder sb){
        sb.append("bbb");
        return sb;
    }

    public void test() {
        String s = "aaa";
        String s2 = ReferencePass.appStr(s);
        //注意这边打印的是s，不是s2
        System.out.println(s);

        StringBuilder sb = new StringBuilder("aaa");
        StringBuilder sb2 = ReferencePass.appSb(sb);
        //注意这边打印的是sb，不是sb2
        System.out.println(sb.toString());
    }
}

class StringTest {
    public void test() {
        String a = "1234";
        System.out.println(Integer.parseInt(a));
        StringBuilder sb = new StringBuilder(123);
        System.out.println(sb);
        String b = "" + Integer.parseInt(a);
        System.out.println(b);
        double e = 1.2;
        a = String.valueOf(e);
        a = new String();
    }
}

/**
 *
 */
class SplitNumAndLetter {
    private String zifu = "ds123d4s4dqwAw57vv58gsTg578q93JNH21dsd2445";
    private int[] num = new int[100];
    private char[] letter = new char[100];
    private int k = 0;
    private int j = 0;

    /**
     * separate numbers and letters
     */
    public void mySplit() {
        for (int i = 0; i < zifu.length(); i++) {
            if (zifu.charAt(i) >= 'A' && zifu.charAt(i) <= 'z') {
                letter[k++] = zifu.charAt(i);
            } else {
                num[j++] = zifu.charAt(i) - 48;
            }
        }
        for (int i = 0; i < k; i++) {
            System.out.print(letter[i]);
        }
        System.out.println();
        for (int i = 0; i < j; i++) {
            System.out.print(num[i]);
        }
//       System.out.println(Arrays.toString(letter));
//       System.out.println(Arrays.toString(num));
    }
}
/**
 * 迷宫最短路路径
 */
/**
 * 给出总数，id，ip有一个主号和马甲，求出主号和马甲
 */

/**
 * aabbaaac的字串是aa，bb，aaa，c，求字串及每个字串的字符个数
 */
class ChildString {
    public void test() {
        int i = 0;
        Scanner sc = new Scanner(System.in);
        StringBuilder s = new StringBuilder(sc.nextLine());
        StringBuilder res = new StringBuilder();
        while (i < s.length() - 1) {
            int count = 1;
            char temp1 = s.charAt(i);
            i++;
            while (true) {
                char temp2 = s.charAt(i);
                i++;
                if (temp1 == temp2) {
                    count++;
                } else {
                    i--;
                    break;
                }
            }
            res.append("" + count + temp1 + " ");
        }
        System.out.println(res);
    }
    public  void test2() {
        long start = System.nanoTime();
        long end;
        int count = 1;
        int j = 0;
        Scanner sc = new Scanner(System.in);
        char[] c = sc.nextLine().toCharArray();
        String[] res = new String[c.length];
        for (int i = 0; i < c.length; ) {
            char temp1 = c[i];
            if (temp1 < 'a' || temp1 > 'z') {
                res = new String[1];
                res[0] = "0";
                break;
            }
            if (++i < c.length) {
                char temp2 = c[i];
                if (temp1 == temp2) {
                    count++;
                } else {
                    res[j++] = "" + count + temp1 + " ";
                    count = 1;
                }
            } else {
                res[j++] = "" + count + temp1 + " ";
                count = 1;
            }
        }
//        long end = System.nanoTime();
//        System.out.println("耗时"+(end-start)/1000+"微秒");

        for (int i = 0; i < res.length; i++) {
            for (int k = i + 1; k < res.length; k++) {
                if (res[i] == null) {
                    break;
                }
                if (res[k] == null) {
                    continue;
                }
                if (res[i].equals(res[k])) {
                    res[k] = null;
                }
            }
        }
        for (String x : res) {
            if (x != null) {
                System.out.print(x);
            }

        }
        end = System.nanoTime();
        System.out.println("耗时"+(end-start)/1000+"微秒");
    }
}

class CompareChar {
    public String encrypt(String info) {
        char[] change = info.toCharArray();
        char num = '1' + '9';
        char up = 'A' + 'Z';
        char down = 'a' + 'z';
        for (int i = 0; i < change.length; i++) {
            if (change[i] >= 'A' && change[i] <= 'Z') {
                change[i] = (char) (up - change[i]);
            }
            if (change[i] >= 'a' && change[i] <= 'z') {
                change[i] = (char) (down - change[i]);
            }
            if (change[i] >= '1' && change[i] <= '9') {
                change[i] = (char) (num - change[i]);
            }
        }
        info = String.valueOf(change);
        return info;
    }
}

/**
 * A='youzan',B='zanyou',切割A为两部分并换位置再连接成B
 */
class StringCatcher {
    /**
     *
     */
    public void print() {
        int count = 0;
        boolean flag = false;
        String c = "aa";
//        StringBuilder b = new StringBuilder("zanyou");
        String b = "zanyou";
        StringBuilder a = new StringBuilder("youzan");
        while (!flag && count < a.length()) {
            char temp = a.charAt(0);
            a.deleteCharAt(0);
            a.append(temp);
            flag = a.toString().equals(b);
            count++;
        }
        System.out.println(flag);
        System.out.println(a);
        System.out.println(count);
    }
    public void stringBuilderTest() {
        String a = "java";
        StringBuilder b = new StringBuilder(a);
        System.out.println(b);
    }
}

/**
 * match child String
 */
class MatchString {
    /**
     *
     */
    public void matchString() {
        int count = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String p = br.readLine();
            Matcher m = Pattern.compile("a").matcher(p);
            Matcher m1 = Pattern.compile("aa").matcher(p);
            Matcher m2 = Pattern.compile("aaa").matcher(p);
            Matcher m3 = Pattern.compile("b").matcher(p);
            Matcher m4 = Pattern.compile("bb").matcher(p);
            if (m.find()) count++;
            if (m.find()) count++;
            if (m.find()) count++;
            if (m.find()) count++;
            if (m.find()) count++;
            System.out.println(count);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}