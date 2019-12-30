package com.yangqihang;

/*
 * File类提供了对当前文件系统中文件的部分操作
 * */

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("src/abc.txt");

        //创建文件
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断文件的属性,返回Boolean类型的值
        file.canExecute();
        file.canRead();
        file.canWrite();

        //判断当前文件是否存在
        System.out.println(file.exists());

        //获取文件的名称
        System.out.println(file.getName());

        //获取文件的绝对路径
        System.out.println(file.getAbsolutePath());
        //获取文件的父目录名称,如果文件的路径中只包含文件名称,则显示空
        System.out.println(file.getParent());
        //返回文件绝对路径的规范格式
        System.out.println(file.getCanonicalPath());
        //返回操作系统的文件分隔符
        System.out.println(file.separator);

        //无论当前文件是否存在,只要给定具体的路径,都可以返回相应的路径名称
        File file1 = new File("d:/");
        System.out.println(file1.getAbsolutePath());

        //判断文件是否是文件或者目录
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());

        String[] list = file1.list();
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("------------------------");
        File[] files = file1.listFiles();
        for (File f : files) {
            System.out.println(f);
        }

        File[] f1 = File.listRoots();
        for (File f : f1) {
            System.out.println(f);
        }

        //创建单级目录
        File file2 = new File("d:/a/b/c");
        file2.mkdir();
        //创建多级目录
        file2.mkdirs();

        //循环遍历输出D盘中所有文件的绝对路径
        //使用递归的方式
        printFile(new File("D:\\java"));
    }

    //文件在遍历的时候,会出现空指针的问题,原因在于当前文件系统受保护,某些文件没有访问权限,此时会报空指针异常
    public static void printFile(File file) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                printFile(f);
            }
        } else {
            System.out.println(file.getAbsolutePath());
        }
    }
}
