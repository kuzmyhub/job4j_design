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
            while ((read = in.readLine()) != null) {
                 if (read.length() > 0
                         && (read.indexOf("#") != 0)) {
                    strArray = read.split("=", 2);
                    if (!read.contains("=")
                            || strArray.length < 2
                            || read.indexOf("=") == 0
                            || read.indexOf("=") == read.length() - 1) {
                        throw new IllegalArgumentException(
                                "Does not match the template \"key=value\""
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