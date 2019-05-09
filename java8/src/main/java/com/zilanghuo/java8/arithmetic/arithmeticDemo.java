package com.zilanghuo.java8.arithmetic;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 算法
 */
public class arithmeticDemo {

    public static void main(String[] args) {
        rotateString("abcdef",2);
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

}
