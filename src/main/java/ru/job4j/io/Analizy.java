package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String read;
            boolean selector = true;
            while ((read = in.readLine()) != null) {
                if ((read.contains("400")
                        || read.contains("500")) && selector) {
                    String[] strArray = read.split(" ", 2);
                    str.append(strArray[1]);
                    str.append(";");
                    selector = false;
                } else if (!selector
                        && !read.contains("400")
                        && !read.contains("500")) {
                    String[] strArray = read.split(" ");
                    selector = true;
                    str.append(strArray[1]);
                    str.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new FileOutputStream(target))) {
            String rsl = str.toString();
            out.println(rsl);
            System.out.println(rsl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new FileOutputStream("unavailable.csv")
        )) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("./server/source.txt", "./server/unavailable.csv");
    }
}
