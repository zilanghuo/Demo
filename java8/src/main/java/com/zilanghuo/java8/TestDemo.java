package com.zilanghuo.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * @author laiwufa
 * @date 2018/10/31
 * use:
 */
public class TestDemo {

    @Test
    public void aa(){
        StaticClass.A a = new StaticClass.A();
    }

    @Test
    public void bb(){
        System.out.println(Stream.iterate("0", i -> "0").limit(4 - 0).reduce(String::concat).get());
    }

    @Test
    public void testList() throws Exception{
        String aa = "/home/duyiping/silkworm-api/testscripts/2780/version-20200904174808-test02-1599212888424-4e7149c/param_test_A.py";
        System.out.println(aa.substring(aa.lastIndexOf("/")+1));



    }

    @Test
    public void testDate() throws Exception{
        String str = "aaas.zip";
        String substring = str.substring(str.lastIndexOf(".")+1, str.length());
        System.out.println(substring);
        System.out.println(str.substring(0,str.lastIndexOf(".")));


    }

    @Test
    public void testHashCode() {
        String s = "OK";
        StringBuffer sb = new StringBuffer(s);
        System.out.println(s.hashCode() + " " + sb.hashCode());
        String b = "OK";
        StringBuffer tb = new StringBuffer(b);
        System.out.println(s.hashCode() + " " + tb.hashCode());
    }

    @org.junit.Test
    public void testLong() {
        int num = 2147483647;
        long temp = num + 2L;
        System.out.println("long:" + num);
        System.out.println("long temp:" + temp);


    }

    @org.junit.Test
    public void testInt() {
        int num = 68;
        char c = (char) num;
        System.out.println(c);
        System.out.println((int) 'A');
    }

    @org.junit.Test
    public void test() {
        Integer a = 333;
        Integer b = 333;
        System.out.println(a == b);
        System.out.println(a.intValue() == b.intValue());

    }

    @org.junit.Test
    public void stringAt() {
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


    public static String encryptAndDencrypt(String value, char secret) {
        byte[] bt = value.getBytes(); // 将需要加密的内容转换为字节数组
        for (int i = 0; i < bt.length; i++) {
            bt[i] = (byte) (bt[i] ^ (int) secret); // 通过异或运算进行加密
        }
        String newresult = new String(bt, 0, bt.length); // 将加密后的字符串保存到 newresult 变量中
        return newresult;
    }

    public static void main(String[] args) {
        char secret = '8';
        String pass = "fasdfdasf. fdsafdsa";
        System.out.println("原字符串内容：" + pass);
        String encryptResult = encryptAndDencrypt(pass, secret);
        System.out.println("加密后的内容：" + encryptResult);
        String uncryptResult = encryptAndDencrypt(encryptResult, secret);
        System.out.println("解密后的内容：" + uncryptResult);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class People {

    private String age;
}