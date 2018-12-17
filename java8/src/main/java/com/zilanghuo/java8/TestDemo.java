package com.zilanghuo.java8;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author laiwufa
 * @date 2018/10/31
 * use:
 */
public class TestDemo {

    @org.junit.Test
    public void test(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().replace("-",""));
    }

    @org.junit.Test
    public void stringAt(){
        Date date = new Date(1544606580709L);
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(date.getTime());//这个就是把时间戳经过处理得到期望格式的时间
        System.out.println("格式化结果0：" + time);


    }


    @org.junit.Test
    public void copyOnWriteArrayList() {
        System.out.println(Integer.SIZE - 3);
        System.out.println(-1 << 2);
        List<Long> arrayList = new ArrayList();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20 * 10000; i++) {
            arrayList.add(System.currentTimeMillis());
        }
        List<Long> arrayListToCopy = new CopyOnWriteArrayList(arrayList);
        // 22464596
        System.out.println("arrayList cost:" + (System.currentTimeMillis() - start));

        List<Long> copyOnWriteArrayList = new CopyOnWriteArrayList(arrayList);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 20 * 10000; i++) {
            copyOnWriteArrayList.add(System.currentTimeMillis());
        }
        // 52130161129
        System.out.println("copyOnWriteArrayList cost:" + (System.currentTimeMillis() - start2));
    }

    @org.junit.Test
    public void subListFailFast() {
        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        List branchList = masterList.subList(0, 3);
        // 通过sublist获取到子集，如果此时再去更改主集，会导致子集的报错
        //masterList.remove(0);
        //masterList.add("ten");
        //masterList.clear();

        branchList.clear();
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        for (Object o : branchList) {
            System.out.println(o);
        }
        // 子集的改变，也会改变主集
        System.out.println(masterList);

    }

}