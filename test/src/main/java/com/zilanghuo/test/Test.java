package com.zilanghuo.test;

import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    @org.junit.Test
    public  void testTwo() throws Exception{
        Date dateline = new Date();
        Thread.sleep(1000);
        if(new Date().after(dateline)){		   //2019-05版本
            System.out.println("1");
        }else if(Objects.equals("on","on")){ //2019-03版本
            System.out.println("2");
        }else{
            System.out.println("3");
        }
    }

    @org.junit.Test
    public void testOne() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(1, 1);
        System.out.println(JSONUtil.toJsonStr(list));

    }

    public static void main(String[] args)  {
        try {
            String cmd = String.format("/Users/admin/Downloads/kettle/data-integration/kitchen.sh -file=/Users/admin/Desktop/yarn_job_param_test.kjb -logfile=/Users/admin/Desktop/120684_test-kettle.log -level 'Debug' -param:instId=\"181608 00\"");
            String cmd2 = String.format("/bin/bash -c /Users/admin/Downloads/kettle/data-integration/kitchen.sh -file=/Users/admin/Desktop/yarn_job_param_test.kjb -logfile=/Users/admin/Desktop/120681_test-kettle.log -level 'Debug' -param:instId=\"181608 00\"");
            System.out.println(cmd);
            //ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",killCommand);
            //Process process = pb.start();
            //int errCode = process.waitFor();
            //System.out.println(errCode);
            //String[] aa = new String[]{"/bin/bash","-c","-param:instId=1020 01",cmd};
             String[] aa = new String[]{"/bin/sh","-c",cmd};

            Process process = Runtime.getRuntime().exec(aa,null,null);
            //Process process = Runtime.getRuntime().exec(cmd2,null,null);

            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static int test() {
        int i = 0;
        try {
            return i;
        } catch (Exception e) {
            return -1;
        } finally {
            i++;
        }
    }

    void getOne() {
        return;
    }

    static void a() {
    }

}
