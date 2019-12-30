package com.yangqihang.exercise;

import java.io.*;
import java.net.URL;

public class BaiduTest {
    public static void main(String[] args) {
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://wwww.baidu.com").openStream(), "utf-8"));
            bufferedWriter = new BufferedWriter((new OutputStreamWriter(new FileOutputStream("baidu.html"))));

            String msg = null;
            while ((msg = bufferedReader.readLine()) != null) {
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
