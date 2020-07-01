package com.zilanghuo.java8.arithmetic;

import cn.hutool.json.JSONUtil;

import java.util.*;

/**
 * 算法
 */
public class ArithmeticDemo {

    public static void main(String[] args) {
        System.out.println(greedy_cut_rope_1(4));
    }

    /**
     * 贪心算法：使所做的选择看起来都是当前最佳的，期望通过所做的局部最优选择来产生一个全局最优解。
     * 给你一个长度为n的绳子，请把绳子剪成m段（m，n都是整数，且都大于1）每段绳子的长度即为K[0],K[1],K[2]...K[m]。请问K[0]*k[1]..*k[m]可能的最大乘积是多少？
     * @param n
     * @return
     */
    public static int greedy_cut_rope_1(int n) {
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        //尽可能多地去减长度为3的绳子段
        int timesOf3 = n / 3;
        //当绳子最后剩下的长度为4的时候，不能再去剪去长度为3的绳子段
        if (n - timesOf3 * 3 == 1)
            timesOf3 -= 1;
        int timesOf2 = (n - timesOf3 * 3) / 2;
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }

    /**
     * 针对一个字符串，获取其频率最高的字符及其出现的第一个位置
     *
     * @param str
     */
    static void getFrequency(String str) {
        char[] arr = str.toCharArray();
        Map<Character, Integer> treeMap = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            int size = treeMap.get(arr[i]) == null ? 0 : treeMap.get(arr[i]);
            treeMap.put(arr[i], size + 1);
        }
        Iterator<Map.Entry<Character, Integer>> iterator = treeMap.entrySet().iterator();
        int maxSize = 0;
        Character maxChar = new Character('c');
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            if (next.getValue() > maxSize) {
                maxSize = next.getValue();
                maxChar = next.getKey();
            }
        }
        System.out.println(maxChar + ":" + maxSize);
    }

    /**
     * 题目：
     * 1、针对数组中的元素，输出全部组装的个数，不在乎顺序
     * 2、枚举一个数组中所有组合
     */
    static void combination(char[] arr, int start, int end) {
        if (start == end) {
            System.out.println(new String(arr));
        }
        for (int i = start; i < arr.length; i++) {
            char temp = arr[start];//交换数组第一个元素与后续的元素
            arr[start] = arr[i];
            // 固定当前的值
            arr[i] = temp;
            combination(arr, start + 1, arr.length);
            temp = arr[start];//将交换后的数组还原
            arr[start] = arr[i];
            arr[i] = temp;
        }

    }

    /**
     * 题目：
     * 1、有一个数值型数组，将奇数放入左边，偶数放入右边，并且最近的偶数是哪一个
     * 2、空间复杂度：1，时间复杂度O(n)
     */
    static void resortArray() {
        int[] arr = new int[]{23, 54, 4, 3, 76, 45, 34, 87, 77, 4};
        int[] newArr = new int[10];
        int jIndex = 0;
        int oIndex = arr.length - 1;
        System.out.println("pre:" + JSONUtil.toJsonStr(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                newArr[oIndex] = arr[i];
                oIndex--;
            } else {
                newArr[jIndex] = arr[i];
                jIndex++;
            }
        }
        System.out.println("first even index:" + (oIndex + 1));
        System.out.println("sort:" + JSONUtil.toJsonStr(newArr));
    }

    /**
     * 题目：
     * 一个三位的数值，它与11的商恰巧等于其每位数的平方
     * 550:50，5*5 +5*5+0*0
     */
    static void thirdCountSame() {
        for (int i = 100; i <= 999; i++) {
            int n = i;
            int j = n / 11;
            int mod = n % 11;
            int sum = 0;
            while (n != 0) {
                sum = sum + (n % 10) * (n % 10);
                n = n / 10;
            }
            if (j == sum && mod == 0) {
                System.out.println("value:" + i);
            }
        }
    }

    /**
     * 题目：字符串：eeeeaaabbcd,输出字符串为4e3a2b1c1d
     *
     * @param str
     */
    static void getCharCount(String str) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Integer integer = map.get(chars[i]);
            System.out.print(chars[i]);
            map.put(chars[i], integer == null ? 1 : integer + 1);
        }
        System.out.println("--------------");
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            System.out.print(next.getValue() + "" + next.getKey());
        }
    }

    /**
     * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，如把字符串“abcdef” 前面的2
     * 个字符'a'和'b'移动到字符串的尾部，使得原字符串变成字符串“cdefab” 。 请写一个函数完成此功能，要
     * 求对长度为n的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)。
     *
     * @param str
     * @param index
     */
    static void rotateString(String str, int index) {
        String preStr = str.substring(index);
        for (int i = 0; i < index; i++) {
            preStr = preStr + str.charAt(i);
        }
        System.out.println(preStr);
    }

    /**
     * 给定一个36位的集合，逢4就踢出，直到剩下最后一个。求最后一个原先的index位置
     * 约瑟夫环:m为全部人数，k为逢k踢出，第几个人出环编号
     */

    static int getLast(int totalCount, int avg, int startIndex) {
        Boolean[] arr = new Boolean[totalCount];
        Arrays.fill(arr, true);
        int atIndex = startIndex - 1;
        int killCount = 0;
        int result = 1;
        while (killCount < totalCount) {
            for (int i = 0; i < avg; i++) {
                // 跳过
                while (!arr[atIndex]) {
                    atIndex = (atIndex + 1) % totalCount;
                }
                if (i == avg - 1) {
                    System.out.println("remove:" + (atIndex + 1));
                    arr[atIndex] = false;
                    killCount++;
                }
                if (killCount == totalCount - 1) {
                    result = atIndex + 1;
                }
                atIndex = (atIndex + 1) % totalCount;
            }
        }
        System.out.println("result:" + result);
        return result;
    }
}