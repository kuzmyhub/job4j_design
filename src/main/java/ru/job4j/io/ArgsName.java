package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    String.format("Key \"%s\" is missing", key
                    ));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            String str = s.replaceFirst("-", "");
            String[] strArr = str.split("=", 2);
            if (strArr.length < 2
                    || strArr[0].isBlank()
                    || strArr[1].isBlank()) {
                throw new IllegalArgumentException(String.format(
                        "\"%s\" does not match the template \"key=value\"", s
                ));
            }
            values.put(strArr[0], strArr[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
