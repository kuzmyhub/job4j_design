package ru.job4j.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> str = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader("log.txt")
        )) {
            in.lines()
                    .filter(x -> x.contains(" 404 "))
                    .forEach(y -> str.add(y));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String str : log) {
            System.out.println(str);
        }
    }
}
