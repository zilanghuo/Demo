package com.zilanghuo.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laiwufa
 * @date 2018/10/31
 * use:
 */
public class ListDemo {

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
