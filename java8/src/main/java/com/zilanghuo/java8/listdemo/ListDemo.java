package com.zilanghuo.java8.listdemo;

import java.util.*;

/**
 * @author laiwufa
 * @date 2019/5/5 0005 ���� 2:30
 */
public class ListDemo {

    public static final int SIZE = 500000;

    public static void main(String[] args) {
        ListDemo demo = new ListDemo();
        ArrayList list = demo.initArrayList();
        LinkedList linkedList = demo.initLinkedList();
        demo.addCostTime(list); // 69702
        demo.addCostTime(linkedList); // 75
        // demo.readCostTime(list);
        // demo.readCostTime(linkedList);
        // demo.readRandomCostTime(list); // 41
        // demo.readRandomCostTime(linkedList); // 263282
        //demo.deleteByPosition(list); // 22761
        // demo.deleteByPosition(linkedList); // 8
        // demo.loopTest(list);
        // demo.loopTest(linkedList);
    }


    // �ж�ָ��Ԫ�ز����ʱ���ʱ
    public void addCostTime(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.add(0, i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("�����ʱ��" + (end.getTime() - start.getTime()));
    }

    // ��ȡԪ�غ�ʱ
    public void readCostTime(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.get(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("��ȡ��ʱ��" + (end.getTime() - start.getTime()));
    }

    // �����ȡԪ��50000ci
    public void readRandomCostTime(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            int j = new Random().nextInt(SIZE);
            list.get(j);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("�����ȡ��ʱ��" + (end.getTime() - start.getTime()));
    }

    public void deleteByPosition(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.remove(0);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("ָ��λ��ɾ����ʱ��" + (end.getTime() - start.getTime()));
    }

    public ArrayList initArrayList() {
        Date start = Calendar.getInstance().getTime();
        ArrayList list = new ArrayList();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("arrayList��ʼ����ʱ��" + (end.getTime() - start.getTime()));
        return list;
    }

    public LinkedList initLinkedList() {
        Date start = Calendar.getInstance().getTime();
        LinkedList list = new LinkedList();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("linkedList��ʼ����ʱ��" + (end.getTime() - start.getTime()));
        return list;
    }

    public void loopTest(List list) {

        Date startTwo = Calendar.getInstance().getTime();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        Date endTwo = Calendar.getInstance().getTime();
        System.out.println("iteѭ����ʱ��" + (endTwo.getTime() - startTwo.getTime()));

        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.get(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("forѭ����ʱ��" + (end.getTime() - start.getTime()));

    }

}
