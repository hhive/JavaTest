package test;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.sun.jmx.remote.internal.ArrayQueue;

import model.Man;
import model.Person;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.DateUtil;
import util.GsonUtil;

/**
 * Main
 */

public class Test1 {

    private static Logger log = Logger.getLogger(Test1.class);

    private int test = 0;

    // Test1() {
    //     System.out.println(test + "test");
    // }
    // static {
    //     System.out.println("test1");
    // }
    //    static {
    //        System.out.println("test3");
    //    }
    /**
     * @param s The object of print
     */
    public static void myPrint(String s) {
        System.out.println(s);
    }

    /**
     * @param arg parameter for main
     */
    public static void main(String[] arg) {

        new Test1().show15();
    }

    /**
     * compare date
     * now 为今天
     * lastDay 为上次报销截止日期
     */
    void show15() {
        Date now = DateUtil.getNowDate();
        Date lastDay = DateUtil.getDate("2020-04-17", DateUtil.DATE_FORMAT_1);
        if (lastDay == null) {
            return;
        }
        System.out.println(DateUtil.differDateInDays(lastDay, now, DateUtil.TimeType.DAY));
    }

    /**
     * son class
     */
    Man show14() {
        return (Man) new Person();
    }

    /**
     * replace
     */
    void show13() {
        String a = "\"aaa\"";
        System.out.println(a);
        System.out.println(a.replace("\"", ""));
    }
    /**
     * file rename
     */
    void fileRename() {
        String path = "E:\\KuGou\\high_quality_music2";
        File file = new File(path);
        File[] fileList = file.listFiles();
        if (fileList != null && fileList.length > 0) {
            for (File file1 : fileList) {
                String parentPath = file1.getParent();
                System.out.println(parentPath);
                String newName = file1.getName().replace(" [mqms2]", "");
                File newFile=new File(parentPath + File.separator + newName);
                System.out.println(file1.renameTo(newFile));
            }
        }
    }
    /**
     * 小数校验
     */
    //两位小数金额校验
    public boolean show12(Object obj) {
        boolean flag = false;
        try {
            if (obj != null) {
                String source = obj.toString();
                // 判断是否是整数或者是携带一位或者两位的小数
                Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
                if (pattern.matcher(source).matches()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return flag;
    }



    /**
     * ip
     */
    public void show11() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("Local HostAddress: " + addr.getHostAddress());
            String hostname = addr.getHostName();
            System.out.println("Local host name: "+hostname);
        } catch (UnknownHostException e) {
            e.getMessage();
        }

    }
    /**
     * 解析地址
     * @author lin
     * @return
     */
    public void addressResolution(){
        String address = "湖北省武汉市洪山区";
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        log.info(table);
    }

    /**
     * String list 遍历
     */
    void show10() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.forEach(s -> {
            s = "6";
        });
        stringList.forEach(s -> {
            log.info(s);
        });
        for (String s : stringList) {
            s = "6";
        }
        for (String s : stringList) {
            log.info(s);
        }
    }
    /**
     * list 遍历
     */
    void show9() {
        List<Person> peopleList = new ArrayList<>();
        Person person = new Person();
        person.setPageNum(1);
        person.setPageSize(8);
        peopleList.add(person);
        person = new Person();
        person.setPageNum(5);
        person.setPageSize(2);
        peopleList.add(person);
        peopleList.forEach((person1) -> {
            person1.setPageNum(9);
        });
        peopleList.forEach((person1) -> {
            log.info(person1.getPageNum() + "; " + person1.getPageSize());
        });
        for (Person person1 : peopleList) {
            person1.setPageNum(6);
        }
        for (Person person1 : peopleList) {
            log.info(person1.getPageNum() + "; " + person1.getPageSize());
        }
    }
    /**
     * set 重复key
     */
    void show8() {
        Set<String> strings = new HashSet<>();
        strings.add("1");
        strings.add("2");
        strings.add("1");
        log.info(strings);
    }

    /**
     * map 重复key
     */
    void show7() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("1", "3");

        System.out.println(map.get("3"));
        for (Map.Entry<String, String> set : map.entrySet()) {
            System.out.println(set.getKey() + ": " + set.getKey());
            set.setValue("3");
        }
        log.info(map);
    }
    /**
     * file \n
     */
    void show6() {
        long pointer = 0L;
        File file = new File("C:\\study\\Java\\JavaTest\\src\\main\\resources\\pdf\\finish\\Making Through the Lens of Culture and Power-Toward Transformative Visions for Educational Equity.pdf.txt");
        if (!file.isFile()) {
            log.error("文件%s为空！" + file);
            return;
        }
        log.info("开始解析txt");
        StringBuilder content = new StringBuilder();
        String line = null;
        try {
            boolean flag = false;
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(pointer);
            while ((line = randomAccessFile.readLine()) != null) {

                if (line.contains("-")) {
                    line = line.replace("-", "");
                    content.append(" ")
                        .append(line);
                    flag = true;
                } else {
                    if (flag) {
                        content.append(line);
                        flag = false;
                    } else {
                        content.append(" ")
                            .append(line);
                    }

                }
                if (line.equals("") || line.endsWith(".")){
                    System.out.println("line" + line);

                    pointer = randomAccessFile.getFilePointer();
                    break;
                }
            }
            if (line == null) {
                pointer = -1L;
            }
            // BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8));
            //
            //
            // while ((line = br.readLine()) != null) { //读取到的内容给line变量
            //     if (line.equals("\n")){
            //         break;
            //     }
            //     content.append(line);
            // }
        } catch (IOException e) {
            log.error("开始解析txt失败" + e);
        }
        System.out.println(content);
    }
    /**
     * pdf index
     */
    void show5() {

    }
    /**
     * Exception
     */
    void show4() {
        int a = 0;
        int b = 11;
        try {
            int c = b / a;
        } catch (Exception e) {
            log.error(e);

            log.error(e.getMessage());
            log.error("e.printStackTrace()");
            e.printStackTrace();
        }
    }
    /** String {} */
    void show3() {
        String a = "3";
        String b = "{a}";
        System.out.println("a " + a);
        System.out.println("b " + b);
    }

    /**
     * HashMap -> Json
     */
    public void show2() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        hashMap.put("2", "3");
        System.out.println(GsonUtil.obj2Json(hashMap));
    }
    /**
     *"_"
     */
    public void show() {
        String temp = "2|";
        String[] a = temp.split("|");
        for (String b : a) {
            System.out.println(b);
        }
        System.out.println(a);
    }
}


//
//        new test3();
// new test2().duiWuRan();
//        String a= "82H,01H,81H,94H,84H,0B4H,0A4H,04H,82H,01H,81H,94H,84H,0C4H,0B4H,04H,82H,01H,81H,0F4H,0D4H,0B4H,0A4H,94H,0E2H,01H,0E1H,0D4H,0B4H,0C4H,0B4H,04H," +
//                "82H,01H,81H,94H,84H,0B4H,0A4H,04H,82H,01H,81H,94H,84H,0C4H,0B4H,04H,82H,01H,81H,0F4H,0D4H,0B4H,0A4H,94H,0E2H,01H,0E1H,0D4H,0B4H,0C4H,0B4H,04H";
//        new test2().stringTest(a);
//        Date date = new java.sql.Date(new java.util.Date().getTime());
//        System.out.println(date);
//        String a = "abc";
//        String b = "a" + "bc";
//        System.out.println(a == b);
//        System.out.println(new test2().jc(4));
//        System.out.println("test2");
//        long i = 42l;
//        System.out.println(i);
//        new JiaJia().test();
//        new TestForVariable().test();
//        RegularExpression a = new RegularExpression();
//        a.first();
//        a.findGroup();
//        a.startEnd();
//        a.matchesTest();
//        new AWithCallback().askQusetion();
//        System.out.println(new String("xyz") == "xyz");
//        System.out.println(new String("xyz") == new String("xyz"));
//        System.out.println(new String("xyz") + "xyz");
//        System.out.println(new String("xyz") + new String("xyz"));

//        new TransientTest().printForTransientWithStatic();

//        try{
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("flyPig.txt")));
//            oos.writeObject(new TestForFinal());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        new TestForFinal();
//        new MyArray().myprint();
//        new ChildClass().childFunction();
//        ParentClass AWithCallback = new ChildClass(4, 5, 6);
//        ChildClass BWithCallback = new ChildClass(1, 2, 3);
//        ((ChildClass) AWithCallback).childFunction(); //true
//        AWithCallback.childFunction(); //false
//        BWithCallback.parentFunction(); //true
//         Object[] AWithCallback= {1,2,3,5,4,8,6,7};
//        perm BWithCallback = new perm(AWithCallback,1,8);
//        System.out.println( AWithCallback[0]);
//        StaticTest staticTest = new StaticTest(String.valueOf("1946"));
//        System.out.println(AWithCallback.isBornBoomer());7
//System.out.println(String.valueOf("1946"));

//new test4().test();
//new BigDecimalTest().test();
// new Hello().show();
// new VariArgs().show();
//new MyTime().show();
//  new mapAndJson().show();
//  new toTime().show();
// new LombokTest().show();
// new Slf4jTest().show();
//        new MapAndJson().show2();
// System.out.println(superClass.value);
//new InToPost().show();
// new googleTranslate().show();
// /**
//  * Google翻译 post
//  */
// class googleTranslate {
//     public void show() {
//         try {
//             //https://translate.google.cn/#view=home&op=translate&sl=auto&tl=zh-CN&text=hello
//             String body1 = "hello";
//             String body = convertToUri(body1);
//             RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body);
//             OkHttpClient okHttpClient = new OkHttpClient();
//             String url = "https://translation.googleapis.com/language/translate/v2";
//             Request request = new Request.Builder().url(url).post(requestBody).build();
//             System.out.println("request is " + request.toString());
//             Response response = okHttpClient.newCall(request).execute();
//             String result = response.body().string();
//             System.out.println(result);
//         } catch (IOException e) {
//             e.getMessage();
//         }
//     }
//     /** 将map格式转换成适合http请求的uri格式 */
//     private String convertToUri(String body) throws UnsupportedEncodingException {
//         //URLEncoder.encode()对请求参数进行转译
//         return URLEncoder.encode(body, "UTF-8");
//     }
// }
//
// /**
//  *利用堆栈将中缀表达式转换成后缀表达式
//  */
// class InToPost {
//     private Stack theStack;
//     private String input;
//     private String output = "";
//     public InToPost() {
//     }
//     public InToPost(String in) {
//         input = in;
//         int stackSize = input.length();
//         theStack = new Stack(stackSize);
//     }
//     public String doTrans() {
//         for (int j = 0; j < input.length(); j++) {
//             char ch = input.charAt(j);
//             switch (ch) {
//                 case '+':
//                 case '-':
//                     gotOper(ch, 1);
//                     break;
//                 case '*':
//                 case '/':
//                     gotOper(ch, 2);
//                     break;
//                 case '(':
//                     theStack.push(ch);
//                     break;
//                 case ')':
//                     gotParen(ch);
//                     break;
//                 default:
//                     output = output + ch;
//                     break;
//             }
//         }
//         while (!theStack.isEmpty()) {
//             output = output + theStack.pop();
//         }
//         //System.out.println(output);
//         return output;
//     }
//     public void gotOper(char opThis, int prec1) {
//         while (!theStack.isEmpty()) {
//             char opTop = theStack.pop();
//             if (opTop == '(') {
//                 theStack.push(opTop);
//                 break;
//             }
//             else {
//                 int prec2;
//                 if (opTop == '+' || opTop == '-')
//                     prec2 = 1;
//                 else
//                     prec2 = 2;
//                 if (prec2 < prec1) {
//                     theStack.push(opTop);
//                     break;
//                 }
//                 else
//                     output = output + opTop;
//             }
//         }
//         theStack.push(opThis);
//     }
//     public void gotParen(char ch){
//         while (!theStack.isEmpty()) {
//             char chx = theStack.pop();
//             if (chx == '(')
//                 break;
//             else
//                 output = output + chx;
//         }
//     }
//     public void show() {
//         String input = "1+2*4/5-7+3/6";
//         System.out.println("input is " + input);
//         String output;
//         InToPost theTrans = new InToPost(input);
//         output = theTrans.doTrans();
//         System.out.println("Postfix is " + output + '\n');
//     }
//     class Stack {
//         private int maxSize;
//         private char[] stackArray;
//         private int top;
//         public Stack(int max) {
//             maxSize = max;
//             stackArray = new char[maxSize];
//             top = -1;
//         }
//         public void push(char j) {
//             stackArray[++top] = j;
//         }
//         public char pop() {
//             return stackArray[top--];
//         }
//         public char peek() {
//             return stackArray[top];
//         }
//         public boolean isEmpty() {
//             return (top == -1);
//         }
//     }
// }
// /**
//  * 测试类的初始化
//  */
// class superClass {
//     static {
//         System.out.println("superClass");
//     }
//     public static int value = 123;
// }
//
// class subClass extends superClass {
//     static {
//         System.out.println("subClass");
//     }
// }
//
// /**
//  * Slf4j
//  */
// class Slf4jTest {
//     public void show() {
//
//         //Person person = Person.builder().name("li").age(28).build();
//
//        // System.out.println(person);
//     }
// }
// /**
//  * 时间操作
//  */
// class toTime {
//     public void show() {
//         java.util.Date now = new java.util.Date();
//         System.out.println(now.toString());
//         Calendar calNow = Calendar.getInstance();
//         calNow.setTime(now);
//         // 将秒、毫秒域清零
//         calNow.set(Calendar.SECOND, 0);
//         calNow.set(Calendar.MILLISECOND, 0);
//         java.util.Date nowReset = calNow.getTime();
//         System.out.println(nowReset.toString());
//         //当前下次发起时间=当前时间秒清0+首次发起间隔时间（分）
//         Calendar calNext = Calendar.getInstance();
//         calNext.setTime(nowReset);
//         calNext.add(Calendar.MINUTE, 2);
//         java.util.Date nextTime = calNext.getTime();
//         System.out.println(nextTime.toString());
//     }
// }
// /**
//  * map与json互转
//  */
// class MapAndJson{
//     /**map转json*/
//     public void show1() {
//         Map<String, String> map = new HashMap<>();
//         map.put("1", "11");
//         map.put("2", "22");
//         System.out.println(new Gson().toJson(map));
//     }
//     /** 对象转json */
//     public void show2() {
//         // Person person = Person.builder()
//         //     .pageNum(1).pageSize(1000).build();
//         // System.out.println(JSON.toJSONString(person));
//     }
// }
// /**
//  * 时间和毫秒互转
//  */
// class MyTime{
//     public void show() {
//         Date date = Date.valueOf("2014-12-01");
//         Long l = date.getTime();
//         System.out.println(l);
//         Date date1 = new Date(l);
//         System.out.println(date1.toString());
//     }
// }
//
// /**
//  * 不定长度参数
//  */
// class VariArgs {
//
//     public void show() {
//         Map<VariArgs, String> a = new HashMap<>();
//         test();
//         test("aaa");
//         test("aaa", "bbb");
//         test("aaa", "bbb", "ccc");
//     }
//
//     public static void test(String... args) {
//         System.out.println(args.getClass());
//         if (args[2] != null) {
//             System.out.println("args[2] != null");
//         }
//         if (args instanceof String[]) {
//             System.out.println("args是数组！");
//         }
//         for (String arg : args) {
//             System.out.println(arg);
//         }
//     }
// }
//
// /**
//  * lombok test
//  */
// class LombokTest {
//
//     public void show () {
//         // person.setAge(28);
//         // person.setName("li");
//         // if (person == null) {
//         //     System.out.println(person.toString() + ": is nulll");
//         // }
//         // if (person.getName() == null) {
//         //     System.out.println("name == null");
//         // }
//         // if (person.getAge() == 0) {
//         //     System.out.println(person.getAge());
//         // }
//         // System.out.println(person.toString());
//         // System.out.println(person);
//     }
// }
//
// /**
//  * BigDecimal及百分号测试
//  */
// class BigDecimalTest {
//     public void test() {
//         BigDecimal a1 = new BigDecimal(10.0 + "").setScale(7, BigDecimal.ROUND_HALF_UP);
//         BigDecimal a2 = new BigDecimal(6.995 + "").setScale(7, BigDecimal.ROUND_HALF_UP);
//
//         BigDecimal r = (a1.subtract(a2)).divide(a1, 4, BigDecimal.ROUND_HALF_EVEN).setScale(4, BigDecimal.ROUND_HALF_UP);
//
//         NumberFormat percent = NumberFormat.getPercentInstance();
//         percent.setMaximumFractionDigits(2);
//         System.out.println(percent.format(r.doubleValue()));
//     }
// }
//
// /**
//  * 移位测试
//  */
// class test4 {
//     int number;
//
//     public void test() {
//         number = 12;
//         mobile(number);
//         number = -12;
//         mobile(number);
//     }
//     /**
//      * 移位操作
//      * 12:1100
//      * 48:110000
//      * 24:11000
//      * 12:1100
//      * -12:11111111111111111111111111110100
//      * -48:11111111111111111111111111010000
//      * -24:11111111111111111111111111101000（32）
//      * 2147483636:1111111111111111111111111110100（31，省略了前面的0）
//      */
//     public void mobile(int num) {
//         //原始数二进制
//         printInfo(num);
//         //左移两位，相当于乘以2的2次方，正负数皆右补0
//         num = num << 2;
//         printInfo(num);
//         //右移一位，相当于除以2的1次方，正数左补0、负数左补1
//         num = num >> 1;
//         printInfo(num);
//         //无符号右移一位，正负数皆左补0
//         num = num >>> 1;
//         printInfo(num);
//     }
//     /**
//      * 输出一个int的二进制数
//      * @param num
//      */
//     private static void printInfo(int num){
//         System.out.print(num + ":");
//         System.out.println(Integer.toBinaryString(num));
//     }
// }
//
// /**
//  *
//  */
// // interface InterfaceTest {
// //     public void test2();
// // }
//
// /**
//  *
//  */
// // class test3{
// //     //定义了形参个数可变的方法
// //     public static void test1(int a,String...str){
// //         for(String tmp:str) System.out.println(tmp);
// //         System.out.println(a);
// //     }
// //     //定义了数组形式的形参方法
// //     public static void test2(int a,String []str){
// //         for(String tmp:str) System.out.println(tmp);
// //         System.out.println(a);
// //     }
// //     public test3(){
// //         test1(1,"tes1测试","test1测试","test1测试");
// //         test2(2,new String[]{"test2测试","test2测试","test2测试"});
// //     }
// // }
//
// /**
//  *
//  */
// class test2 {
//
//     public void stringTest(String a) {
//         String tmp[] = a.trim().split("H,");
//         for (int i = 0; i < tmp.length; i++) {
//             tmp[i] = "0x" + tmp[i] + ",";
//         }
//         for (int i = 0; i < tmp.length; i++) {
//             System.out.print(tmp[i]);
//         }
//     }
//     //long的字符串转换
//     public void test(){
//
//        Integer a = new Integer(1);
//        Integer b = 2;
//        int i = a.intValue();
//        a = 111234;
//        String c = "" + a;
//        c += a;
//        Long e = Long.parseLong(c);
//         System.out.println(e);
//     }
//     public int jc(int num) {
//
//         if (1 == num) {
//             return num;
//         } else {
//             num = num * jc(num - 1);
//         }
//         return num;
//     }
//     public void sleepAnfInter() throws Exception {
//         Thread t = new Thread()  {
//             public void run() {
//                 System.out.println(1);
//             }
//         };
//         try {
//             Thread.sleep(2000);
//         } catch (InterruptedException e) {
//             System.out.println(e);
//             e.printStackTrace();
//             System.out.println(4);
//             throw new RuntimeException("2");
//         }
//         t.start();
//         t.join();
//         System.out.println(3);
//     }
//     public void doubleTest() {
//         double e = 3.14;
//         double a = 3.14D;
//         double b = 5.2e12;
//         System.out.println(e);
//         System.out.println(b);
//         System.out.println(a);
//         String c = "abc";
//         final String d = c;
//         c = "efg";
//         System.out.println(c);
//         System.out.println(d);
//     }
//     public void jiaJiaTest() {
//         int x = 0;
//         int y = 0;
//         int k = 0;
//         for (int z = 0; z < 5; z++) {
//             if ((++x > 2) && (++y > 2) && (k++ > 2)) {
//                 x++;
//                 ++y;
//                 k++;
//             }
//         }
//         System.out.println(x + "" +y + "" +k);
//     }
//     public static Boolean forEachTest1(char c) {
//         System.out.println(c);
//         return true;
//     }
//     public void forEachTest2() {
//         int i = 0;
//         for (forEachTest1('A'); forEachTest1('B') && (i < 2); forEachTest1('C')) {
//             i++;
//             forEachTest1('D');
//         }
//     }
//     public int finallyTest() {
//
//         try {
//             System.out.println(1 / 0);
//             return 1;
//         } catch (Exception e) {
//             System.out.println(2);
//             return 2;
//         } finally {
//             System.out.println(3);
//             return 3;
//         }
//     }
//     public void leiJai(long a) {
//         long s = 0;
//         String temp = "";
//         Scanner sc = new Scanner(System.in);
//         int size = sc.nextInt();
//         for (int i = 0; i < size; i++) {
//             temp += a;
//             System.out.println(temp);
//             s += Long.parseLong(temp);
//         }
//         System.out.println(s);
//     }
//     public void beverages() {
//         int n = 1;
//         int m = 0;
//         for (int i = 9; i > 0; i--) {
//             if (3 == n) {
//                 n = 1;
//                 i++;
//             } else {
//                 n++;
//             }
//             m++;
//         }
//         System.out.println(m);
//     }
//     public void test3() {
//         int count = 0;
//         for (long i = 1000000; i < 10000000; i++) {
//             if (i % 8 == 1 && i % 9 == 1) {
//                 count += i;
//
//             }
//         }
//         System.out.println(count);
//     }
// //    public void duiWuRan() {
// //        List list = new ArrayList<>();
// //        list.add(20);
// //        System.out.println(list.get(0) instanceof Integer);
// //        System.out.println(list.get(0));
// //        List<String> ls = list;
// //        System.out.println(ls.get(0));
// //    }
// }
//
// /**
//  *
//  */
// class JiaJia {
//     static {
//         System.out.println(1);
//     }
//     {
//         System.out.println("第一构造块");
//     }
//     JiaJia() {
//         System.out.println(2);
//     }
//
//         public void test() {
//         int count = 0;
//         int num = 0;
//         int count2 = 0;
//         for (int i = 0; i <= 100; i++) {
//             num = num + i;
//             count = count++;
//             count2++;
//         }
//         System.out.println(num * count);
//         System.out.println(num * count2);
//     }
//     public static void main(String[] arg) {
//         System.out.println("jia");
//     }
// }
//
// /**
//  *
//  */
// class TestForVariable {
//     int a;
//     byte b;
//     short c;
//     long d;
//     boolean e;
//     String f;
//     float g;
//     double h;
//
//     /**
//      *
//      */
//     public void test() {
//         int k;
//         System.out.println(a);
//         System.out.println(b);
//         System.out.println(c);
//         System.out.println(d);
//         System.out.println(e);
//         System.out.println(f);
//         System.out.println(g);
//         System.out.println(h);
//         /**
//          * k must be initialized being used
//          */
// //        if (k < 1) {
// //            System.out.println(k);
// //        }
//     }
// }
//
// /**
//  * The test of Regular Expression(regex)
//  */
// class RegularExpression {
//     /**
//      *
//      */
//     public void first() {
//         Pattern p = Pattern.compile("a*b"); //ab false
//         Matcher m = p.matcher("aaaab");
//         boolean b = m.matches();
//         System.out.println(b); //true
//         //boolean b = Pattern.matches("a*b", "aaaab");//true
//     }
//
//     /**
//      * find() and group()
//      */
//     public void findGroup() {
//         String str = "I want to make friends,my tel:18579115585;"
//                 + "My club needs a partner,please contact me:13054083365;"
//                 + "I want to sell some old books,contact me if you want,18579896532.";
//         System.out.println(str);
//         Matcher m = Pattern.compile("((13\\d+)|(18\\d+))\\d{8}").matcher(str);
//         System.out.println(m);
//         while (m.find()) {
//             System.out.println(m.group());
//         }
//
//     }
//
//     /**
//      * start and end and replace
//      */
//     public void startEnd() {
//         String regStr = "Java is very very very easy!";
//         System.out.println("The aim of string: " + regStr);
//         Matcher m = Pattern.compile("\\w+").matcher(regStr);
//         while (m.find()) {
//             System.out.println(m.group() + ", child string start position: "
//                     + m.start() + ",the position of end: " + m.end());
//         }
//         Matcher m1 = Pattern.compile("ve\\w*").matcher(regStr);
//
//         System.out.println(m1.replaceAll("hello"));
// //        m1.replaceFirst("hello");
// //        System.out.println(regStr);
//
//
//     }
//
//     /**
//      * looking() and reset()
//      */
//     public void matchesTest() {
//         String[] mails = {
//                 "kongyeku@163.com",
//                 "kongyeku@gmail.org",
//                 "wawa@abc.xx",
//                 "ligang@foxmail.com"
//         };
//         String mailRegex = "\\w{3,20}@\\w+.(com|org|cn|net|gov)";
//         Pattern mailPattern = Pattern.compile(mailRegex);
//         Matcher mailMatcher = null;
//         for (String mail : mails) {
//             if (null == mailMatcher) {
//                 mailMatcher = mailPattern.matcher(mail);
//             } else {
//                 mailMatcher.reset(mail);
//             }
//             String result = mail + (mailMatcher.matches() ? " is " : " not ") + "a valid address of mail";
//             System.out.println(result);
//         }
//         //"kongyeku@163.com".matches("\\w{3,20}@\\w+.(com|org|cn|net|gov)");
//     }
// }
//
// /**
//  * Use AWithCallback interface to subscribe to business logic ,
//  * if it is not to exist ,it is ok
//  */
// interface Callback {
//     /**
//      * Response callback function
//      */
//     void slove();
// }
//
// /**
//  * Implement the above interface,
//  * registration implementation class of callback and response callback
//  */
// class AWithCallback implements Callback {
//     private BWithCallback bWithCallback = new BWithCallback();
//
//     /**
//      * response callback function
//      */
//     public void slove() {
//         System.out.println("AWithCallback received the message that BWithCallback has solve the problem");
//     }
//
//     /**
//      * Registration callback function
//      */
//     public void askQusetion() {
//         /**
//          * Do other things yourself
//          */
// //        new Thread(new Runnable() {
// //            @Override
// //            public void run() {
// //                System.out.println("AWithCallback want to do another thing!");
// //            }
// //        }).start();
//         new Thread(() -> System.out.println("AWithCallback want to do another thing!")).start();
//         /**
//          * ask BWithCallback to solve this problem
//          */
//         this.bWithCallback.call(this);
//     }
// }
//
// /**
//  * Implement callback function
//  */
// class BWithCallback {
//     /**
//      * @param aWithCallback test
//      */
//     public void call(Callback aWithCallback) {
//         /**
//          * BWithCallback help AWithCallback to slove the problem
//          */
//         System.out.println("BWithCallback help AWithCallback to solve the problem!");
//         /**
//          * call back
//          */
//         aWithCallback.slove();
//     }
// }
//
// /**
//  * Use transient to not serialize AWithCallback variable,
//  * Note that when reading,the order of reading data must be
//  * consistent with the order in which the data is stored
//  *
//  * @author Alexia
//  */
// class TransientTest {
//
//     /**
//      *
//      */
//     public void printForTransientWithStatic() {
//
//         User user = new User();
//         user.setId("01");
//         user.setUsername("Alexia");
//         user.setPasswd("123456");
//         user.setSex("male");
//         user.setIdentity("student");
//
//         System.out.println("read before Serializable: ");
//         System.out.println("id:" + user.getId());
//         System.out.println("username: " + user.getUsername());
//         System.err.println("password: " + user.getPasswd());
//         System.out.println("sex:" + user.getSex());
//         System.out.println("identity:" + user.getIdentity());
//
//         try {
//             ObjectOutputStream os = new ObjectOutputStream(
//                     new FileOutputStream("D:\\JavaProject\\MyJava\\Miscellaneous\\user.txt"));
//             //Write the object of User to file
//             os.writeObject(user);
//             os.flush();
//             os.close();
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         try {
//
//             //Change the value of username before Deserialize
//             User.username = "wang";
//
//             ObjectInputStream is = new ObjectInputStream(new FileInputStream(
//                     "D:\\JavaProject\\MyJava\\Miscellaneous\\user.txt"));
//             //Read the data of User from Stream
//             user = (User) is.readObject();
//             is.close();
//
//             System.out.println("\nread after Serializable: ");
//             System.out.println("id:" + user.getId());
//             System.out.println("username: " + user.getUsername());
//             System.err.println("password: " + user.getPasswd());
//             System.err.println("sex: " + user.getSex());
//             System.out.println("identity:" + user.getIdentity());
//
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         } catch (ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//     }
// }
//
// /**
//  * test for Serializable
//  */
// class User implements Serializable {
//     private static final long serialVersionUID = 8294180014912103005L;
//     public static String username;
//     private String id;
//     private transient String password;
//     private String sex;
//     private String identity;
//     private boolean isTrue;
//
//     public boolean isTrue() {
//         return isTrue;
//     }
//
//     public void setTrue(boolean aTrue) {
//         isTrue = aTrue;
//     }
//
//     /**
//      * @return test
//      */
//     public String getId() {
//         return id;
//     }
//
//     /**
//      * @param id test
//      */
//     public void setId(String id) {
//         this.id = id;
//     }
//
//     /**
//      * @return test
//      */
//     public String getUsername() {
//         return username;
//     }
//
//     /**
//      * @param username test
//      */
//     public void setUsername(String username) {
//         this.username = username;
//     }
//
//     /**
//      * @return test
//      */
//     public String getPasswd() {
//         return password;
//     }
//
//     /**
//      * @param password test
//      */
//     public void setPasswd(String password) {
//         this.password = password;
//     }
//
//     /**
//      * @return test
//      */
//     public String getSex() {
//         return sex;
//     }
//
//     /**
//      * @param sex test
//      */
//     public void setSex(String sex) {
//         this.sex = sex;
//     }
//
//     /**
//      * @return test
//      */
//     public String getIdentity() {
//         return identity;
//     }
//
//     /**
//      * @param identity test
//      */
//     public void setIdentity(String identity) {
//         this.identity = identity;
//     }
//
// }
//
// /**
//  * test for final
//  */
// class TestForFinal {
//     private int test = 0;
//
//     /**
//      *
//      */
//     TestForFinal() {
//         String aWithCallback = "hello2";
//         final String bWithCallback = "hello";
//         String d = "hello";
//         String c = bWithCallback + 2;
//         System.out.println(c);
//         String e = d + 2;
//         System.out.println(e);
//         //Because of FINAL,in the place where BWithCallback is used,BWithCallback will be directly replaced with its value
//         System.out.println(aWithCallback == c);
//         System.out.println(aWithCallback == e);
//
//         final String f = getHello();
//         String g = f + 2;
//         System.out.println(aWithCallback == f);
//
//         //After the reference variable is modified by FINAL,
//         // it can no longer point to other object(change),
//         // but the content of the object it points to is variable
//         final MyClassForFinal h = new MyClassForFinal();
//         System.out.println(++h.i);
//         //The difference between STATIC and FINAL,STATIC variable can change
//         MyClassForFinal myClass1 = new MyClassForFinal();
//         MyClassForFinal myClass2 = new MyClassForFinal();
//         System.out.println(myClass1.k);
//         System.out.println(myClass1.j);
//         System.out.println(myClass2.k);
//         System.out.println(myClass2.j);
//         final StringBuilder builder = new StringBuilder("hello");
//         builder.append("world");
//         System.out.println(builder);
//         //builder = new StringBuilder("good");
//     }
//
//     /**
//      * @return test
//      */
//     public static String getHello() {
//
//         return "hello";
//     }
//
//
// }
//
// /**
//  *
//  */
// class MyClassForFinal {
//     public static double k = Math.random();
//     final double j = Math.random();
//     int i = 0;
// }
// ///**
// // * test for split and char
// // */
// //class SplitAndChar{
// //    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// //    /**
// //     *
// //     */
// //    SplitAndChar(){
// //        try{
// //            String in = br.readLine();
// //            char[] Sp = in.split(",");
// //        } catch (Exception e){
// //            e.printStackTrace();
// //        }
// //
// //    }
// //}
//
// /**
//  * The test of super
//  */
// class ParentClass {
//     private int i = 0;
//     private int j = 0;
//     protected String z = "parent";
//     String g = "parent2";
//     /**
//      * null constructor
//      */
//     ParentClass(){
//
//     }
//
//     /**
//      * @param i test
//      * @param j test
//      */
//     ParentClass(int i, int j) {
//         this.i = i;
//         this.j = j;
//     }
//
//     /**
//      * test
//      */
//     public void parentFunction() {
//         System.out.println(1);
//     }
//     public void onlyParent() {
//         System.out.println(2);
//     }
// }
//
// /**
//  *
//  */
// class ChildClass extends ParentClass {
//     private int k;
//     String z = "child";
//
//     ChildClass() {}
//     ChildClass(int i, int j, int k) {
//         //If the constructor of parent class has parameters(no empty constructor),
//         //the child class must be explicitly called with "super"
//         super(i, j);
//         this.k = k;
//     }
//
//     /**
//      * test
//      */
//     public void childFunction() {
//         System.out.println(z + " " + super.z + " " + g);
//     }
// }
//
// /**
//  * //排列问题
//  */
// class Perm {
//     private int aWithCallback;
//
//     /**
//      * @param list test
//      * @param k    test
//      * @param m    test
//      */
//     public void perm1(Object[] list, int k, int m) {
//         if (k == m) {
//             for (int i = 0; i <= m; i++) {
//                 System.out.println(list[i]);
//             }
//             System.out.println();
//         } else {
//             for (int i = k; i <= m; i++) {
//                 swap(list, k, i);
//                 perm1(list, k + 1, m);
//                 swap(list, k, i);
//             }
//         }
//
//     }
//
//     /**
//      * @param list test
//      * @param k    test
//      * @param i    test
//      */
//     public void swap(Object[] list, int k, int i) {
//         Object temp;
//         temp = list[k];
//         list[k] = list[i];
//         list[i] = temp;
//
//     }
//
//
// }
//
// /**
//  * static
//  */
// class StaticTest {
//     private static String startString, endString;
//
//     static {
//
//         startString = String.valueOf("1946");
//         endString = String.valueOf("1964");
//     }
//
//     private String birthString;
//
//     /**
//      * @param birthString test
//      */
//     StaticTest(String birthString) {
//         this.birthString = birthString;
//     }
//
//     /**
//      *
//      */
//     public static void staticWay() {
//         System.out.println("This is a STATIC way");
//     }
//
//     /**
//      * @return test
//      */
//     boolean isBornBoomer() {
//         Queue queue;
//         ArrayQueue arrayQueue = new ArrayQueue(20);
//         return birthString.compareTo(startString) >= 0 && birthString.compareTo(endString) < 0;
//     }
// }


/**
 * The algorithm of chess with robot
 * https://www.cnblogs.com/DevLegal/p/8831894.html
 */
//public interface IRobot {
//    static final Random rand = new Random();
//
//    /**
//     * There we provide AWithCallback default implementation to simulate robot's behavior
//     *
//     * @return AWithCallback {@code robot.Pair} which contains AWithCallback valid (x,y) position
//     */
//    default ChessWithRobot.Pair getDeterminedPos() {
//        return new ChessWithRobot.Pair(rand.nextInt(15) + 1, rand.nextInt(15) + 1);
//    }
//
//    /**
//     * This method is used to retrieve game board such that robot can determine its (x,y) position
//     * @param gameBoard the 2-dimension array to represent the game board
//     */
//    void retrieveGameBoard(int[][] gameBoard);
//}
///**
// * The algorithm of chess with robot
// */
//class Pair {
//    public int x;
//    public int y;
//
//    Pair() {}
//    Pair(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}
///**
// * The algorithm of chess with robot
// */
//class StupidRobot implements IRobot {
//    private static final int BOARD_SIZE = 15;
//    private static final int ROLE_OPPONENT = 1;
//    private static final int ROLE_ROBOT = 2;
//    private static final int ROLE_NON = 0;
//    private static final int ORIENTATION_LR = 0;
//    private static final int ORIENTATION_UD = 1;
//    private static final int ORIENTATION_LT_RD = 2;
//    private static final int ORIENTATION_RT_LD = 3;
//
//    private int[][] boardRef = null;
//
//
//    /**
//     * There we provide AWithCallback default implementation to simulate robot's behavior
//     *
//     * @return AWithCallback {@code robot.Pair} which contains AWithCallback valid (x,y) position
//     */
//    @Override
//    public Pair getDeterminedPos() {
//        int[][] situationRobot  = new int[boardRef.length][boardRef[0].length];
//        int[][] situationOpponent  = new int[boardRef.length][boardRef[0].length];
//
//        int maxRobotScore = 0;
//        Pair maxRobotPoint = new Pair();
//
//        int maxOpponentScore = 0;
//        Pair maxOpponentPoint = new Pair();
//        for(int i=0;i<BOARD_SIZE;i++){
//            for(int k=0;k<BOARD_SIZE;k++){
//                if(boardRef[i][k]!=ROLE_NON){
//                    situationOpponent[i][k]=situationRobot[i][k]=0;
//                }else{
//                    boardRef[i][k] = ROLE_OPPONENT;
//                    situationOpponent[i][k] = evaluateScore(ROLE_OPPONENT,i,k);
//                    boardRef[i][k]=ROLE_NON;
//                    if(situationOpponent[i][k]>maxOpponentScore){
//                        maxOpponentScore = situationOpponent[i][k];
//                        maxOpponentPoint.x = i;
//                        maxOpponentPoint.y = k;
//                    }
//
//                    boardRef[i][k]=ROLE_ROBOT;
//                    situationRobot[i][k]=evaluateScore(ROLE_ROBOT,i,k);
//                    boardRef[i][k]=ROLE_NON;
//                    if(situationRobot[i][k]>maxRobotScore){
//                        maxRobotScore = situationRobot[i][k];
//                        maxRobotPoint.x = i;
//                        maxRobotPoint.y = k;
//                    }
//
//                }
//            }
//        }
//        if(maxRobotScore > maxOpponentScore || maxRobotScore==Integer.MAX_VALUE){
//            return maxRobotPoint;
//        }else{
//            return maxOpponentPoint;
//        }
//    }
//
//    /**
//     * This method is used to retrieve game board such that robot can determine its (x,y) position
//     *
//     * @param gameBoard the 2-dimension array to represent the game board
//     */
//    @Override
//    public void retrieveGameBoard(int[][] gameBoard) {
//        boardRef = gameBoard;
//    }
//
//
//    /**
//     * The policy of evaluating was referred to https://www.cnblogs.com/maxuewei2/p/4825520.html
//     * @param role the role of current player
//     * @param x position x
//     * @param y position y
//     * @param orientation orientation of determining line
//     * @return
//     */
//    private int patternRecognition(int role, int x,int y,int orientation){
//        StringBuilder sb = new StringBuilder();
//        if(orientation==ORIENTATION_LR){
//            int leftBound = (x - 4)>=0?x-4:0;
//            int rightBound = (x +4)<BOARD_SIZE?x+4:BOARD_SIZE-1;
//
//            for(int i=leftBound;i<=rightBound;i++){
//                sb.append(boardRef[i][y]);
//            }
//        }else if(orientation == ORIENTATION_UD){
//            int bottomBound = (y+4)<BOARD_SIZE?y+4:BOARD_SIZE-1;
//            int topBound = (y-4)>=0?y-4:0;
//
//            for(int i=topBound;i<=bottomBound;i++){
//                sb.append(boardRef[x][i]);
//            }
//        }else if(orientation== ORIENTATION_LT_RD){
//            int leftBound = 0,rightBound = 0,bottomBound = 0,topBound = 0;
//
//            for(int i=1;i<=4;i++){
//                leftBound = x-i;
//                topBound = y-i;
//                if(leftBound<0||topBound<0){
//                    leftBound++;
//                    topBound++;
//                    break;
//                }
//            }
//            for(int k=1;k<=4;k++){
//                rightBound = x+k;
//                bottomBound = y+k;
//                if(rightBound>BOARD_SIZE||bottomBound>BOARD_SIZE){
//                    rightBound--;
//                    bottomBound--;
//                    break;
//                }
//            }
//            for(int i=topBound,k=leftBound;i<=bottomBound && k<=rightBound;i++,k++){
//                sb.append(boardRef[k][i]);
//            }
//        }else if(orientation== ORIENTATION_RT_LD){
//            int leftBound = 0,rightBound = 0,bottomBound = 0,topBound = 0;
//
//            for(int i=1;i<=4;i++){
//                rightBound = x+i;
//                topBound = y-i;
//                if(rightBound>BOARD_SIZE||topBound<0){
//                    rightBound--;
//                    topBound++;
//                    break;
//                }
//            }
//            for(int k=1;k<=4;k++){
//                leftBound = x-k;
//                bottomBound = y+k;
//                if(leftBound<0||bottomBound>BOARD_SIZE){
//                    leftBound++;
//                    bottomBound--;
//                    break;
//                }
//            }
//
//            for(int i=topBound,k=rightBound;i<=bottomBound && k>=leftBound;i++,k--){
//                sb.append(boardRef[k][i]);
//            }
//        }
//        String str = sb.toString();
//        if(str.contains(role == ROLE_ROBOT ? "22222" : "11111")){
//            return Integer.MAX_VALUE;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "022220" : "011110")){
//            return 300000;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22202" : "11101") ||
//                str.contains(role == ROLE_ROBOT ? "20222" : "10111")){
//            return 3000;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "0022200" : "0011100")){
//            return 3000;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22022" : "11011")){
//            return 2600;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22220" : "11110")||
//                str.contains(role == ROLE_ROBOT ? "02222" : "01111")){
//            return 2500;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "020220" : "010110")||
//                str.contains(role == ROLE_ROBOT ? "022020" : "011010")){
//            return 800;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "00022000" : "00011000")){
//            return 650;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "20022" : "10011")||
//                str.contains(role == ROLE_ROBOT ? "22002" : "11001")){
//            return 600;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "20202" : "10101")){
//            return 550;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22200" : "11100")||
//                str.contains(role == ROLE_ROBOT ? "00222" : "00111")){
//            return 500;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "0020200" : "0010100")){
//            return 250;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "020020" : "010010")){
//            return 200;
//        }
//        if(str.contains(role == ROLE_ROBOT ? "22000" : "11000")||
//                str.contains(role == ROLE_ROBOT ? "00022" : "00011")){
//            return 150;
//        }
//        return 0;
//    }
//
//    private int evaluateScore(int role,int x, int y){
//        int AWithCallback = patternRecognition(role,x,y,ORIENTATION_RT_LD);
//        int BWithCallback = patternRecognition(role,x,y,ORIENTATION_LT_RD);
//        int c = patternRecognition(role,x,y,ORIENTATION_UD);
//        int d = patternRecognition(role,x,y,ORIENTATION_LR);
//        return Math.max(Math.max(Math.max(AWithCallback,BWithCallback),c),d);

//    }
//}
//interface Queue<E> {
//    int getSize();
//    boolean isEmpty();
//    void enqueue(E e);
//    E dequeue();
//    E getFront();
//}
//
//class ArrayQueue<E> implements Queue<E> {
//
//    private Array<E> array;
//    public ArrayQueue(int capacity){
//        array=new Array<>(capacity);
//    }
//    public ArrayQueue(){
//        array=new Array<>();
//    }
//    @Override
//    public int getSize(){
//        return array.getSize();
//    }
//
//    @Override
//    public boolean isEmpty(){
//        return array.isEmpty();
//    }
//    public int getCapacity(){
//        return array.getCapacity();
//    }
//
//    @Override
//    public void enqueue(E e){
//        array.addLast(e);
//    }
//
//    @Override
//    public E dequeue(){
//        return array.removeFirst();
//    }
//    @Override
//    public E getFront(){
//        return array.getFirst();
//    }
//    @Override
//    public String toString(){
//
//        StringBuilder res = new StringBuilder();
//        res.append( "Queue:");
//        res.append("front [");
//        for(int i = 0 ; i < array.getSize() ; i ++){
//            res.append(array.get(i));
//            if(i != array.getSize() - 1)
//                res.append(", ");
//        }
//        res.append("]tail");
//        return res.toString();
//    }
//
//
//}



