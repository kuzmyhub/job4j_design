package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {

        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            StringBuilder str = new StringBuilder();
            String read;
            while ((read = in.readLine()) != null) {
                boolean selector = false;
                if (!selector
                        && (read.contains("400")
                        || read.contains("500"))) {
                    String[] strArray = read.split(" ");
                    str.append(strArray[2]);
                    selector = true;
                } else if (selector) {
                    String[] strArray = read.split(" ");
                    str.append(";");
                    str.append(strArray[2]);
                    str.append(System.lineSeparator());
                    selector = false;
                }
            }
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
    }
}
