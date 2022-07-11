package ru.job4j.java.io;

import com.sun.source.tree.UsesTree;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            String strNum = sb.toString();
            String[] strArray = strNum.split(System.lineSeparator());
            for (int i = 0; i < strArray.length; i++) {
                System.out.println((Integer.parseInt(strArray[i]) % 2) == 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
