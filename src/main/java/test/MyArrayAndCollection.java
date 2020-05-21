package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

import javax.swing.JFrame;

/**
 * interface for LambdaQa test
 */
interface MyInterface {
    /**
     * test
     */
    interface Eatable {
        /**
         * test
         */
        void taste();
    }

    /**
     * test
     */
    interface Flyable {
        /**
         * @param weather test
         */
        void fly(String weather);
    }

    /**
     * test
     */
    interface Addable {
        /**
         * @param a test
         * @param b test
         * @return test
         */
        int add(int a, int b);
    }

    /**
     * test
     */
    @FunctionalInterface
    interface Converter {
        /**
         * @param from test
         * @return test
         */
        Integer convert(String from);
    }

    /**
     * test
     */
    @FunctionalInterface
    interface MyTest {
        /**
         * @param a test
         * @param b test
         * @param c test
         * @return test
         */
        String test(String a, int b, int c);
    }

    /**
     *
     */
    @FunctionalInterface
    interface YourTest {
        /**
         * @param title test
         * @return test
         */
        JFrame win(String title);
    }

    /**
     *
     * @param e test
     */
}

/**
 * Main
 */

public final class MyArrayAndCollection {
    /**
     * Constructor
     */
    private MyArrayAndCollection() {
        System.out.println("test");
    }

    /**
     * @param arg test
     */
    public static void main(String[] arg) {

        //new MyMap().bianLi();
//        int[] a = {1,2};
//        System.out.println(Arrays.binarySearch(a,1));
//        String[] b = {"玩家1","玩家2"};
//        System.out.println(Arrays.binarySearch(b,"玩家1"));
//        ShowHand showHand = new ShowHand();
//        showHand.play(showHand);
//        Book book = new Book();
//        ArrayList<Book> arrayList = new ArrayList<>();
//        book.setTitle("a");
//        book.setPrice(1);
//        arrayList.add(book);
//        book.setTitle("b");
//        book.setPrice(2);
//        arrayList.add(book);
//        for (Book book1 : arrayList) {
//            System.out.println(book.getTitle() + "," + book.getPrice());
//        }
//        GoLang goLang = new GoLang();
//        goLang.printCheckBoard();
//        new ThreadForChess1(goLang).start();
//        new ThreadForChess1(goLang).start();

//        MyMap myMap = new MyMap();
//        myMap.printForHashMap();
//        MyQueue myQueue = new MyQueue();
//        myQueue.printForQueue();
//        MyList myList = new MyList();
//        myList.printForList();

//        ParentClass a = new ChildClass(4, 5, 6);
//        ChildClass b = new ChildClass(1, 2, 3);
//        ((ChildClass) a).childFunction(); //true
//        a.childFunction(); //error
//        b.parentFunction(); //true

//        new MySet().printForTreeSet();
//        LambdaQs lambdaQs = new LambdaQs();
//        lambdaQs.PrintForLam(lambdaQs);

//        MyCollection myCollection = new MyCollection();
//        myCollection.printForCollection();
//        myCollection.printForStream();
        //myCollection.printForIterator();

//        new splitNumAndLetter().mySplit();

//        new MyArray().myPrint();
        new HashMapEx().test();
    }
}

class HashMapEx{
    public void test() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 1);
        hashMap.put(2, 2);

        Iterator<Integer> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            if (key.equals(1)) {
                hashMap.remove(key);
                //iterator.remove();
            }
        }
    }
}

class CollectionTest {
    public void test() {
        ArrayList list = new ArrayList(10);
        list.add(10);
        System.out.println(list.size());

    }
}

/**
 * test2 for showHand
 */
class ShowHand {
    private final int MAX_PLAY_NUM = 5;
    int counter = 0;
    int firstPos = 0;
    private int PLAY_NUM = 5;
    private String[] types = {"方块", "草花", "红心", "黑桃"};
    private String[] values = {"2", "3", "4", "5", "6",
            "7", "8", "9", "10", "J", "Q", "K", "A"};
    private List<String> cards = new LinkedList<String>();
    private String[] players = new String[MAX_PLAY_NUM];
    //the element of Array is List
    private List<String>[] playersCards = new List[MAX_PLAY_NUM];
    private LinkedList<String> temp = new LinkedList<String>();
    //Two-dimensional List
    private List<LinkedList<String>> originalCards = new LinkedList<>();

    /**
     * Initialize the deck.
     */
    public void initCards() {
        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                cards.add(types[i] + values[j]);
            }
        }
        Collections.shuffle(cards);
        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                temp.add(types[i] + values[j]);
            }
            originalCards.add(temp);
            temp = new LinkedList<String>();
        }

//        for (int i = 0; i < types.length; i++) {
//            for (int j = 0; j < values.length; j++) {
//                //if (originalCards.get(i).get(j).equals("黑桃3")) {
//                    System.out.print(originalCards.get(i).get(j) + " ");
//                //}
//            }
//        }
//        System.out.println(originalCards.get(3));
//        System.out.println(originalCards.get(3).get(2));
//        System.out.println(originalCards.contains("黑桃3"));
//        System.out.println(cards.indexOf("黑桃3"));
//        Iterator iterator = originalCards.iterator();
//        Iterator iterator2 = cards.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }
//        System.out.println();
//        while (iterator2.hasNext()) {
//            System.out.print(iterator2.next() + " ");
//        }
    }

    /**
     * @param names The Array to store the name of players
     */
    public void initPlayer(String... names) {
        if (names.length > MAX_PLAY_NUM || names.length < 2) {
            System.out.println("The number of player is error");
            return;
        } else {
            for (int i = 0; i < names.length; i++) {
                players[i] = names[i];
            }
        }
        PLAY_NUM = names.length;
    }

    /**
     * Initialize the List that store the cards belong to player
     */
    public void initPlayerCards() {
        for (int i = 0; i < PLAY_NUM; i++) {
            if (players[i] != null && !players[i].equals("")) {
                playersCards[i] = new LinkedList<String>();
            }
        }
    }

    /**
     * Simulated licensing
     */
    public void deliverCard(String firstPlayer) {
        counter++;
        //get index by the content of element
//        String[] b = {"玩家1","玩家2"};
//        System.out.println(Arrays.binarySearch(b,firstPlayer));
//        for (int i = 0; i < players.length; i++)
//        System.out.println(players[i]);
//        System.out.println(Arrays.binarySearch(players, firstPlayer));
        int first = Arrays.binarySearch(players, firstPlayer);
        System.out.println(first);
        if (first < 0) {
            firstPos = firstPos + 1;
            if (firstPos >= PLAY_NUM) {
                firstPos = 0;
            }
        } else {
            firstPos = first;
        }
        for (int i = firstPos; i < PLAY_NUM; i++) {
            if (players[i] != null) {
                playersCards[i].add(cards.get(0));
                cards.remove(0);
            }
        }
        for (int i = 0; i < firstPos; i++) {
            if (players[i] != null) {
                playersCards[i].add(cards.get(0));
                cards.remove(0);
            }
        }
        if (5 == counter) {
            cards.clear();
        }
//        for (int i = 0; i < 3; i++) {
//            System.out.println(playersCards[i].get(0));
//        }
    }

    /**
     * Show the handCards except the first
     */
    public void showPlaysCards() {
        for (int i = 0; i < PLAY_NUM; i++) {
            if (players[i] != null) {
                System.out.print(players[i] + "： ");
                for (int j = 1; j < playersCards[i].size(); j++) {
                    System.out.print(playersCards[i].get(j) + " "); //peekLast()?
                }
            }
            System.out.println();
        }
    }

    /**
     * compare to the size of the last card ,return the biggest one
     */
    public String specificSize() {
        int max = 0;
        int k = 0;
        int z = 1;
        boolean flag = false;
        int[] onrTurnI = new int[PLAY_NUM];
        int[] onrTurnJ = new int[PLAY_NUM];
        for (int y = 0; y < PLAY_NUM; y++) {
            for (int i = 0; i < types.length; i++) {
                for (int j = 0; j < values.length; j++) {
                    if (originalCards.get(i).get(j).equals(playersCards[y].get(playersCards[y].size() - 1))) {
                        onrTurnI[k] = i;
                        onrTurnJ[k] = j;
                        k++;

                    }
                }

            }
        }
        while (z < PLAY_NUM) {
            if (onrTurnJ[z] > onrTurnJ[max]) {
                max = z;
                z++;
            } else if (onrTurnJ[z] == onrTurnJ[max]) {
                if (onrTurnI[z] > onrTurnI[max]) {
                    max = z;
                    z++;
                } else {
                    z++;
                }
            } else {
                z++;
            }
        }
        return players[max];
    }

    /**
     *
     */
    public void isBet(String firstPlayer) {
        firstPos = Arrays.binarySearch(players, firstPlayer);
        for (int i = firstPos; i < PLAY_NUM; i++) {
            i = toBet(i);
        }
        for (int i = 0; i < firstPos; i++) {
            toBet(i);
        }
    }

    /**
     * Decide whether to place a bet
     */
    private int toBet(int i) {
        Scanner in = new Scanner(System.in);
        if (players[i] != null) {
            System.out.println(players[i] + "是否下注？请输入\"y\"或者\"n\"");
            String sc = in.nextLine();
            System.out.println(i + "," + sc + "," + PLAY_NUM);
            while (!sc.equals("y") && !sc.equals("n")) {
                System.out.println(!sc.equals("y") + "," + !sc.equals("n"));
                System.out.println("输入错误，" + players[i] + "请输入\"y\"或者\"n\"");
                sc = in.nextLine();
                System.out.println(sc);
            }
            if (sc.equals("n")) {
                for (int j = i; j < PLAY_NUM - 1; j++) {
                    players[j] = players[j + 1];
                }
                i--;
                PLAY_NUM--;
            }
        }
        return i;
    }

    /**
     * The process of simulating a game
     *
     * @param showHand The object
     */
    public void play(ShowHand showHand) {
        showHand.initPlayer("玩家1", "玩家2"); //NullPointerException
        //showHand.initPlayer("玩家1", "玩家2", "玩家3");
        showHand.initCards();
        showHand.initPlayerCards();
        showHand.deliverCard("玩家1");
        //showHand.showPlaysCards();
        showHand.deliverCard("玩家1");
        showHand.showPlaysCards();
        while (PLAY_NUM > 1 && cards.size() > 0) {
            String firstPlayer = showHand.specificSize();
            System.out.println("这轮" + firstPlayer + "最大");
            isBet(firstPlayer);
            showHand.deliverCard(firstPlayer);
            showHand.showPlaysCards();
        }
        if (1 == PLAY_NUM) {
            System.out.println("只剩一位玩家，" + players[0] + "win");
        }
        if (0 == cards.size()) {
            System.out.println("这轮五张牌已发完毕，显示玩家的所有牌，请比较大小");
            for (int i = 0; i < PLAY_NUM; i++) {
                if (players[i] != null) {
                    System.out.print(players[i] + "： ");
                    for (int j = 0; j < playersCards[i].size(); j++) {
                        System.out.print(playersCards[i].get(j) + " "); //peekLast()?
                    }
                }
                System.out.println();
            }
        }
    }
}

/**
 *
 */
class ThreadForChess1 extends Thread {
    private GoLang goLang;

    /**
     *
     */
    ThreadForChess1(GoLang goLang) {
        this.goLang = goLang;
    }

    /**
     *
     */
    public void run() {
        while (!goLang.chess(goLang)) {
            continue;
        }
    }
}

/**
 * Test1 for Array, Gomoku
 */
class GoLang {
    private final int boardSize = 15;
    private int xPos;
    private int yPos;
    private int role = 1;
    private String[][] chessboard = new String[boardSize][boardSize];
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * initial check board
     */
    GoLang() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                chessboard[i][j] = "✖ ";
            }
        }
        chessboard[5][6] = "❤ ";
        chessboard[6][7] = "❤ ";
        chessboard[7][8] = "❤ ";
        chessboard[8][9] = "❤ ";
    }

    /**
     * print the check board
     */
    public void printCheckBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(chessboard[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Adjust who is win
     *
     * @param symbol pieces
     *               private boolean checkWin() {//检测当前是否由五子连线的方法，简述一下，这个方法其实很简单，
     *               只要我们在每一次落子的时候检查是否由五子连线就可以确保一旦有人胜出，我们就可以马上发现。先检查横线和竖线，再检查左右斜线。
     *               <p>
     *               boolean flag = false; //设置的标志，当由五子连线时就返回flag=false
     *               int count = 1;        //计数当前由几颗棋子相连
     *               int symbol = chessboard[x][y];
     *               int i = 1;
     *               <p>
     *               while(((x+i)<16)&&symbol == chessboard[x+i][y]) {
     *               count++;
     *               i++;
     *               }
     *               i = 1;
     *               while(((x-i)>=1)&&symbol == chessboard[x-i][y]) {
     *               count++;
     *               i++;
     *               }
     *               if(count>=5)
     *               {flag = true;}
     *               //Other direction
     *               int count2 = 1;
     *               int i2 = 1;
     *               while(((y+i2)<16) && symbol == chessboard[x][y+i2]) {
     *               count2++;
     *               i2++;
     *               }
     *               i = 1;
     *               while(((y-i2)>=1)&&symbol == chessboard[x][y-i2]) {
     *               count2++;
     *               i2++;
     *               }
     *               if(count2>=5)
     *               {flag = true;}
     *               <p>
     *               int count3 = 1;
     *               int i3 = 1;
     *               while(((y-i3)>=1)&&((x+i3)<16)&&symbol == chessboard[x+i3][y-i3]) {
     *               count3++;
     *               i3++;
     *               }
     *               i = 1;
     *               while(((x-i3)>=1)&&((y+i3)<16)&&symbol == chessboard[x-i3][y+i3]) {
     *               count3++;
     *               i3++;
     *               }
     *               if(count3>=5)
     *               {flag = true;}
     *               <p>
     *               int count4 = 1;
     *               int i4 = 1;
     *               while(((y-i4)>=1)&&((x-i4)>=1)&&symbol == chessboard[x-i4][y-i4]) {
     *               count4++;
     *               i4++;
     *               }
     *               i = 1;
     *               while(((x+i4)<16)&&((y+i4)<16)&&symbol == chessboard[x+i4][y+i4]) {
     *               count4++;
     *               i4++;
     *               }
     *               if(count4>=5)
     *               {flag = true;}
     *               <p>
     *               return flag;
     *               <p>
     *               }
     *               }
     * @return return
     */
    private Boolean checkWin(String symbol) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (symbol == chessboard[i][j]) {
                    if (j <= 10 && symbol == chessboard[i][j + 1] && symbol == chessboard[i][j + 2]
                            && symbol == chessboard[i][j + 3] && symbol == chessboard[i][j + 4]) {
                        return true;
                    }
                    if (i <= 10 && symbol == chessboard[i + 1][j] && symbol == chessboard[i + 2][j]
                            && symbol == chessboard[i + 3][j] && symbol == chessboard[i + 4][j]) {
                        return true;
                    }
                    if (i <= 10 && j <= 10 && symbol == chessboard[i + 1][j + 1] && symbol == chessboard[i + 2][j + 2]
                            && symbol == chessboard[i + 3][j + 3] && symbol == chessboard[i + 4][j + 4]) {
                        return true;
                    }
                    if (i <= 10 && j >= 4 && symbol == chessboard[i + 1][j - 1] && symbol == chessboard[i + 2][j - 2]
                            && symbol == chessboard[i + 3][j - 3] && symbol == chessboard[i + 4][j + 4]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Input validity judgement
     *
     * @param inputStr input
     * @return return
     */
    private boolean inputValidity(String inputStr) {
        String[] posStrArr;
        try {
            posStrArr = inputStr.split(",");
            if (posStrArr[0].charAt(0) - 48 >= 1
                    && posStrArr[0].charAt(0) - 48 <= 15
                    && posStrArr[1].charAt(0) - 48 >= 1
                    && posStrArr[1].charAt(0) - 48 <= 15) {
                xPos = Integer.parseInt(posStrArr[0]);
                yPos = Integer.parseInt(posStrArr[1]);
                return true;
            } else {
                System.out.println("Illegal input,Please input number between 1~15");
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Illegal input,Please input number between 1~15;\n" + e);
            return false;
        }
    }

    /**
     * Playing chess
     *
     * @param goLang The object of GoLang
     * @return return
     */
    public synchronized boolean chess(GoLang goLang) {
        String symbol;
        if (3 == role) {
            return true;
        }
        System.out.println("Please enter the coordinates of x,y" + "（" + role + "）" + ":");
        try {
            if (inputValidity(br.readLine())) {
                if (1 == role && "✖ " == goLang.chessboard[yPos - 1][xPos - 1]) {
                    goLang.chessboard[yPos - 1][xPos - 1] = "❤ ";
                    symbol = "❤ ";
                    if (goLang.checkWin(symbol)) {
                        System.out.println(role + "win");
                        role = 3;
                        return true;
                    }
                    role = 2;
                } else if ("✖ " == goLang.chessboard[yPos - 1][xPos - 1]) {
                    goLang.chessboard[yPos - 1][xPos - 1] = "★ ";
                    symbol = "★ ";
                    if (goLang.checkWin(symbol)) {
                        System.out.println(role + "win");
                        role = 3;
                        return true;
                    }
                    role = 1;
                } else {
                    System.out.println("There are already pieces here,please retry");
                }
                goLang.printCheckBoard();
                notifyAll();
                wait();
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return false;
    }
}

/**
 * Operational tests on Map
 * Implement class: MyHashMap, Hashtable(Properties), LinkedHashMap,
 * SortedMap, TreeMap, weakHashMap, IdentityHashMap, EnumMap
 */
class MyMap {
    private Map<String, Integer> map = new HashMap<>();

    /**
     * print
     */
    public void printForHashMap() {
        map.put("Java", 103);
        map.put("Java EE", 10);
        map.put("AJAX", 79);
        map.put("Android", 79);
        System.out.println(map.put("Java EE", 99));
        System.out.println(map);
        System.out.println("is include Java?" + map.containsKey("Java"));
        System.out.println("is include 99?" + map.containsValue(99));
        for (Object key : map.keySet()) {
            System.out.println(key + "-->" + map.get(key));
        }
        map.remove("AJAX");
        map.merge("Java", 10, (oldVal, param) -> (Integer) oldVal + (Integer) param);
        System.out.println(map);
        map.computeIfPresent("Java", (key, value) -> ((String) key).length());
        System.out.println(map);
    }
    public void bianLi() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "ab");
        map.put("4", "ab");
        map.put("5", "ab");// 和上面相同 ， 会自己筛选
//        Map<String,String> map=new MyHashMap<String,String>();
//        for(int i=0;i<10000;i++){
//            map.put(String.valueOf(i),String.valueOf(i) );
//        }

        System.out.println(map.size());
        // 第一种：
        /*
         * Set<Integer> set = map.keySet(); //得到所有key的集合
         *
         * for (Integer in : set) { String str = map.get(in);
         * System.out.println(in + "     " + str); }
         */
        System.out.println("第一种：通过Map.keySet遍历key和value：");
        long start = System.nanoTime();
        for (String in : map.keySet()) {
            //map.keySet()返回的是所有key的值
            String str = map.get(in);//得到每个key多对用value的值
            System.out.println(in + "     " + str);
        }
        long end = System.nanoTime();
        System.out.println("第一种耗时：" + (end - start)/1000 + "微秒");

        // 第二种：
        System.out.println("第二种：通过Map.entrySet使用iterator遍历key和value：");
        start = System.nanoTime();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        end = System.nanoTime();
        System.out.println("第二种耗时：" + (end - start)/1000 + "微秒");

        // 第三种：推荐，尤其是容量大时
        System.out.println("第三种：通过Map.entrySet遍历key和value");
        start = System.nanoTime();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
            //entry.getKey() ;entry.getValue(); entry.setValue();
            //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
            System.out.println("key= " + entry.getKey() + " and value= "
                    + entry.getValue());
        }
        end = System.nanoTime();
        System.out.println("第三种耗时：" + (end - start)/1000 + "微秒");
        // 第四种：

        System.out.println("第四种：通过Map.values()遍历所有的value，但不能遍历key");
        start = System.nanoTime();
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
        end = System.nanoTime();
        System.out.println("第四种耗时：" + (end - start)/1000 + "微秒");

    }
}

/**
 * Operational tests on Queue
 * The queue does not allow random access to element int the queue
 * but ArrayDeque could;
 * Implement class: PriorityQueue, ArrayDeque
 */
class MyQueue {
    //use Arrayeque as stack
    private ArrayDeque stack = new ArrayDeque();

    private ArrayDeque queue = new ArrayDeque();

    /**
     * print the test of ArrayDeque
     */
    public void printForStack() {
        stack.push("Java");
        stack.push("Java EE");
        stack.push("Android");
        System.out.println(stack);
        System.out.println(stack.peek() + ",do not delete");
        System.out.println(stack.pop() + ",delete");
        System.out.println(stack);
    }

    /**
     * print the test of ArrayDeque
     */
    public void printForQueue() {
        queue.offer("Java");
        queue.offer("Java EE");
        queue.offer("Android");
        System.out.println(queue);
        System.out.println(queue.peek() + ",do not delete");
        System.out.println(queue.pop() + ",delete");
        System.out.println(queue);
    }
}

/**
 * The test of List
 * Implement class: ArrayLis, Vector, LinkedList
 */
class MyList {
    //Use initialCapacity to specify the length
    private List books = new ArrayList();

    /**
     * Operational tests on List
     */
    public void printForList() {
        //ArrayList use initialCapacity to specify the length
        ArrayList a = new ArrayList(12);
        //add capacity
        a.ensureCapacity(10);

        books.add(new String("Java"));
        books.add("Android");
        books.add(new String("Java EE"));
        System.out.println(books);
        //Insert a new String into the second position
        books.add(1, new String("AJAX"));

        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        books.remove(2);
        System.out.println(books);
        //Determine the position of the specified element in the List collection,
        // print "1",indicates that it is in second place
        //Note: it uses "new"
        System.out.println(books.indexOf(new String("AJAX")));
        books.set(1, new String("Java"));
        //Intercept the first element(include) of the collection
        // to the second element(not included) into a subset
        System.out.println(books.subList(1, 2));

//        String s = new String("s");
//        String s1 = "s";
//        System.out.println(s.equals(s1));

        books.sort((o1, o2) -> ((String) o1).length() - ((String) o2).length());
        System.out.println(books);
        //Replace the element with the length of the element;
        books.replaceAll(ele -> ((String) ele).length());
        System.out.println(books);
        //Comparing Iterator with listIterator,
        // you can see that listIterator adds the function of forward iteration
        //It needs to have elements in front of it
        books.listIterator().hasPrevious();

    }
}

/**
 * Set is basically the same as Collection,
 * but Set dose not allow duplicate elements;
 * Implement class: HashSet, LinkedHashSet, TreeSet, EnumSet;
 */
class MySet {
    /**
     * print the test of EnumSet
     */
    public void printForEnum() {
        EnumSet es4 = EnumSet.range(Season.Summer, Season.Winter);
        System.out.println(es4);
        EnumSet es5 = EnumSet.complementOf(es4);
        System.out.println(es5);
    }

    /**
     * print the test of TreeSet
     * It implements SortSet
     */
    public void printForTreeSet() {
        //Custom Sort of TreeSet
        TreeSet ts = new TreeSet((o1, o2) -> {
            M m1 = (M) o1;
            M m2 = (M) o2;
            //Determine the size according to the "age" attribute
            // of the M`s object,age bigger M`s object is smaller
            return m1.age > m2.age ? -1 : m1.age < m2.age ? 1 : 0;
        });
        ts.add(new M(5));
        ts.add(new M(-3));
        ts.add(new M(9));
        System.out.println(ts);
    }

    /**
     * Enum class
     */
    enum Season {
        Spring, Summer, Fall, Winter
    }

    /**
     * Custom Sort of TreeSet
     */
    class M {
        private int age;

        /**
         *
         */
        M(int age) {
            this.age = age;
        }

        /**
         * @return implement "toString" to print M`s object,
         * otherwise it will print the address of M`s object
         */
        public String toString() {
            return "M[age:" + age + "]";
        }
    }
}

/**
 * The usage of LambdaQa
 */
class LambdaQs implements MyInterface {

    /**
     * @param e test
     */
    public void eat(Eatable e) {
        //输出对象e
        System.out.println(e);
        e.taste();
    }

    /**
     * @param f test
     */
    public void drive(Flyable f) {
        System.out.println("我开：" + f);
        f.fly("晴天");
    }

    /**
     * @param add test
     */
    public void toAdd(Addable add) {
        System.out.println("5+3:" + add.add(5, 3));
    }

    /**
     * @param lq test
     */
    public void printForLam(LambdaQs lq) {

        //There is only one statement , you can omit the curly braces
        lq.eat(() -> System.out.println("苹果味道好！"));
        //There is only one parameter ,you can omit parenthesis
        lq.drive(weather -> {
            System.out.println("今天天气是：" + weather);
            System.out.println("直升机飞行平稳");
        });
        //There is only one statement,
        // so you can omit the "return" keyword even if the expression requires a return values
        //why can I get his object by implementing its abstract method?
        lq.toAdd((a, b) -> a + b);
        //Cast an expression using a functional interface(Forced type conversion
        Object obj = (Runnable) () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println();
            }
        };
    }

    /**
     *
     */
    public void printForLamRefer() {
        //Converter converter = from -> Integer.valueOf(from);
        Converter converter1 = Integer::valueOf;
        Integer val = converter1.convert("99");
        //方法引用替代Lamdba表达式：引用类方法
        //函数式接口中被实现方法的全部参数传给该类方法作为参数
        System.out.println(val);

        //Converter converter2 = from -> "fkit.org".indexOf(from);
        Converter converter3 = "fkit.org"::indexOf;
        //indexOf 方法返回一个整数值，指出 String 对象内子字符串的开始位置。如果没有找到子字符串，则返回-1。
        Integer value = converter3.convert("it");
        System.out.println(value);

        //YourTest yt = (String a) -> new JFrame(a);
        YourTest yt1 = JFrame::new;
        JFrame jf = yt1.win("我的窗口");
        System.out.println(jf);

        //MyTest mt = (a, b, c) -> a.substring(b, c);
        MyTest mt1 = String::substring;
        String str = mt1.test("Java I Love you", 2, 9);
        System.out.println(str);
    }
}

/**
 *
 */
class MyCollection {
    private Collection c = new ArrayList();
    private Collection books = new HashSet();
    //Synchronous control
    private Collection c1 = Collections.synchronizedCollection(new ArrayList());
    private List list = Collections.synchronizedList(new ArrayList());
    private Set s = Collections.synchronizedSet(new HashSet());
    private Map m = Collections.synchronizedMap(new HashMap());


    /**
     *
     */
    public void printForCollection() {
        c.add("孙悟空");
        c.add(6);
        System.out.println("c的元素数：" + c.size());
        c.remove(6);
        System.out.println("c中是否包含“孙悟空”字符串：" + c.contains("孙悟空"));
        c.add("Java");
        System.out.println("c集合的元素：" + c);

        books.add("Java");
        books.add("Android");
        System.out.println("c集合是否完全包含books集合？" + c.containsAll(books));
        //用c集合减去books集合里的元素
        c.removeAll(books);
        System.out.println("c中的元素：" + c);
        c.clear();
        System.out.println("c中的元素：" + c);
        //控制books集合中只剩下c集合里也包含的元素
        books.retainAll(c);
        System.out.println("books集合的元素：" + books);
        books.add("q");
        books.add("a");
        books.add("w");
        for (Object obj : books) {
            System.out.println(obj);
        }
    }

    /**
     * print usage for Iterator
     */
    public void printForIterator() {
        books.add("q");
        books.add("a");
        books.add("w");
        //使用Lambda表达式，调用forEach()方法遍历集合
        books.forEach(obj -> System.out.println("迭代集合元素：" + obj));
        Iterator it = books.iterator();
        System.out.println(it.hasNext());
        while (it.hasNext()) {
            //it.next()返回的是Object类型
            String book = (String) it.next();
            System.out.println("book:" + book);
            //使用Iterator迭代过程中，不可修改集合元素，类似books.remove(book)
            if (book.equals("a")) {
                it.remove();
            }
            book = "b";
        }
        System.out.println(books);
    }

    /**
     *
     */
    public void printForStream() {
        //stream()将集合转换成Stream
        System.out.println(books.stream()
                .filter(ele -> ((String) ele).contains("Java"))
                .count());
        System.out.println(books.stream()
                .filter(ele -> ((String) ele).length() > 10)
                .count());
        //mapToInt()获取原有的Stream对应的IntStream
        books.stream().mapToInt(ele -> ((String) ele).length())
                .forEach(System.out::println);
    }
}

/**
 * Array
 */
class MyArray {
    private int[] c = {1, 2, 3};
    private int[][] d = {{1, 2, 3, 4, 5},
            {1, 33, 8, 9, 7}};
    private String[][] str1 = new String[][]{new String[3], new String[]{"Hello"}};
    private String[][] str2 = {new String[3], new String[]{"Hello"}};

    /**
     *
     */
    public void myPrintForArray() {
        String[] strings1 = {"Hello"};
        System.out.println(d[1][3]);
        int[] arr1 = {3, -4, 25, 16, 30, 18};
        //排序,利用了cpu并行提高性能
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(arr1[1] == 3);
        int[] arr2 = {3, 4, 25, 16, 30, 18};
        //Arrays.parallelPrefix(arr2, (left,right)->left * right);
        Arrays.parallelPrefix(arr2, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left * right;
            }
        });
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = new int[5];
        Arrays.parallelSetAll(arr3, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand * 5;
            }
        });
        System.out.println(Arrays.toString(arr3));
        //insert element
        ArrayList<String> list = new ArrayList<String>(); // 增加元素到list对象中
        list.add("Item1");
        list.add("Item2");
        list.add(2, "Item3"); // 此条语句将会把“Item3”字符串增加到list的第3个位置。
        list.add("Item4");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

