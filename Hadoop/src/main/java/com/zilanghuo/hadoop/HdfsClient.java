package com.zilanghuo.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

/**
 * Created by laiwufa on 2019-10-22
 */
public class HdfsClient {

    FileSystem fs = null;

    @Before
    public void init() throws Exception {


        Configuration conf = new Configuration();
        // conf.set("fs.defaultFS", "hdfs://node-1:9000");
        /**
         * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是jar中默认配置
         */
        // 获取一个hdfs的访问客户端
        fs = FileSystem.get(new URI("hdfs://10.0.68.231:8022"), conf, "root");
        //fs = FileSystem.get(new URI("hdfs://hadoop03-dev.bigdata.cn:8022"), conf, "root");

    }

    /**
     * 往hdfs上传文件
     *
     * @throws Exception
     */
    @Test
    public void testAddFileToHdfs() throws Exception {
        // 要上传的文件所在的本地路径
        // 要上传到hdfs的目标路径*/
        Path src = new Path("/Users/admin/Downloads/pi.py");
        Path dst = new Path("/tmp/laiwufa/pi.py");
        fs.copyFromLocalFile(src, dst);

        fs.close();
    }

    /**
     * 从hdfs中复制文件到本地文件系统
     *
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Test
    public void testDownloadFileToLocal() throws IllegalArgumentException, IOException {

        // fs.copyToLocalFile(new Path("/mysql-connector-java-5.1.28.jar"), new
        // Path("d:/"));
        fs.copyToLocalFile(false, new Path("/tmp/laiwufa/aa.txt"), new Path("/Users/admin/Downloads"), true);
        fs.close();

    }

    /**
     * 目录操作
     *
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Test
    public void deleteFile() throws IllegalArgumentException, IOException {
        // 删除文件夹 ，如果是非空文件夹，参数2必须给值true ，删除所有子文件夹
        fs.delete(new Path("/tmp/laiwufa"), true);
    }

    /**
     * 目录操作
     *
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Test
    public void testMkdirAndDeleteAndRename() throws IllegalArgumentException, IOException {

        // 创建目录
        fs.mkdirs(new Path("/a1/b1/c1"));

        // 删除文件夹 ，如果是非空文件夹，参数2必须给值true ，删除所有子文件夹
        fs.delete(new Path("/aaa"), true);

        // 重命名文件或文件夹
        fs.rename(new Path("/a1"), new Path("/a2"));

    }

    /**
     * 查看目录信息，只显示文件
     *
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     */
    @Test
    public void testListFiles() throws FileNotFoundException, IllegalArgumentException, IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getLen());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation bl : blockLocations) {
                System.out.println("block-length:" + bl.getLength() + "--" + "block-offset:" + bl.getOffset());
                String[] hosts = bl.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("--------------打印的分割线--------------");
        }

    }

    /**
     * 查看文件及文件夹信息
     *
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     */
    @Test
    public void testListAll() throws FileNotFoundException, IllegalArgumentException, IOException {
        //可以右击方法名，Run 测试一下。
        FileStatus[] listStatus = fs.listStatus(new Path("/tmp/laiwufa/"));

        String flag = "";
        for (FileStatus fstatus : listStatus) {

            if (fstatus.isFile()) {
                flag = "f-- ";
            } else {
                flag = "d-- ";
            }
            System.out.print(flag + fstatus.getPath().getName()+" ");
            System.out.println(fstatus.getPermission());
        }

    }

}
