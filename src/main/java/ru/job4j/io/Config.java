package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(
                new FileReader(this.path)
        )) {
            String[] strArray;
            String read;
            int count = -1;
            while ((read = in.readLine()) != null) {
                count++;
                if (!read.isBlank()
                        && !read.startsWith("#")
                        && read.contains("=")) {
                    strArray = read.split("=", 2);
                    if (strArray[0].isBlank()
                            || strArray[1].isBlank()) {
                        throw new IllegalArgumentException(
                                "String №"
                                        + count
                                        + " does not match the template \"key=value\""
                        );
                    }
                    values.put(strArray[0], strArray[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(values);
    }

    public String value(String key) {
        throw new UnsupportedOperationException(
                "Don't impl this method yet!"
        );
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(
                new FileReader(this.path)
        )) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        Config conf = new Config("app.properties");
        conf.load();
    }
}