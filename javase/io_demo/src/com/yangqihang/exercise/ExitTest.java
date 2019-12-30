package com.yangqihang.exercise;

import java.io.*;

public class ExitTest {
    public static void main(String[] args) {

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) {
            String str = "";
            while (!str.equals("exit")) {
                str = bufferedReader.readLine();
                bufferedWriter.write(str);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

