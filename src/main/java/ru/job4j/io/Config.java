package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                if (!read.isBlank()
                        && !read.startsWith("#")) {
                    strArray = read.split("=", 2);
                    if (!read.contains("=")
                            || strArray[0].isBlank()
                            || strArray[1].isBlank()) {
                        throw new IllegalArgumentException(
                                String.format(
                                        "String %s does not match the template \"key=value\"",
                                        read
                                ));
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
        Path file = Path.of(this.path);
        if (!Files.isRegularFile(file)) {
            throw new IllegalArgumentException(String.format(
                    "Not file %s", this.path
            ));
        }
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "%s is missing", key
            ));
        }
        return values.get(key);
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