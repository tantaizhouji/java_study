package com.yangqihang;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.Permission;
import com.yangqihang.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Springbootoa06ApplicationTests {
    @Autowired
    AccountMapper accMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 测试多表查询
     */
    @Test
    void selectByPermission() {
        List<Account> list = accMapper.selectByPermission();
        System.out.println(list);
    }

    /**
     * 测试空集合
     */
    @Test
    void hasAuth() {
        List<Permission> perList = new ArrayList<>();

        String uri = "/account/profile";
        for (Permission permission : perList) {
            if (uri.startsWith(permission.getUri())) {
                System.out.println("uri: " + uri);
                return;
            }
        }

        System.out.println("no");
    }

    /**
     * 文件上传路径测试
     */
    @Test
    void uploadFile() {

        try {
            String clazzpath = ResourceUtils.getURL("classpath:").getPath();
            System.out.println(clazzpath);
            if (clazzpath.startsWith("/D:/")) {
                System.out.println("Windows");
                return;
            } else if (clazzpath.startsWith("/root/")) {
                System.out.println("Linux");
                return;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //新建存放文件夹的实例
        String classpath = "d:/static/uploads";
        File file = new File(classpath);
        //判断文件是否存在
        //不存在该文件
        if (!file.exists()) {
            System.out.println("没有该文件夹，需要创建");
            //创建文件夹
            file.mkdirs();
            System.out.println("创建文件夹成功");
        }

        try {
            //获取classpath的文件夹的实例
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            System.out.println("path: " + path);
            //获取classpath下存放上传文件的文件夹实例
            File upload = new File(path.getAbsolutePath(), "static/uploads");
            System.out.println("upload: " + upload);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
