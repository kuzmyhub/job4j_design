package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;
import java.io.File;
import java.util.Map;

public class CSVReader {

    private final Map<String, String> values = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        CSVReader csvReader = CSVReader.of(args);
        File file = new File(csvReader.values.get("path"));
        Scanner columnScanner = new Scanner(file);
        String[] columnNames = columnScanner.nextLine().split(";");
        String[] columnFilter = csvReader.values.get("filter").split(",");
        int[] columnNumbers = new int[columnFilter.length];
        int columArrIndex = 0;
        for (String cf : columnFilter) {
            int counter = 0;
            for (String cn : columnNames) {
                if (cf.equals(cn)) {
                    columnNumbers[columArrIndex] = counter;
                }
                counter++;
            }
            columArrIndex++;
        }
        for (int i : columnNumbers) {
            System.out.println(i);
        }
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        if (csvReader.values.get("out").equals("stdout")) {
            while (scanner.hasNextLine()) {
                String[] tmp = scanner.nextLine().split(";");
                for (int i = 0; i < columnNumbers.length; i++) {
                    stringBuilder.append(tmp[columnNumbers[i]]);
                    if (i < columnNumbers.length - 1) {
                        stringBuilder.append(csvReader.values.get("delimiter"));
                    } else {
                        stringBuilder.append(System.lineSeparator());
                    }
                }
            }
            System.out.println(stringBuilder);
        }
    }

    private void parse(String[] args) {
        for (String s : args) {
            String[] strArr = s.split("=", 2);
            if (strArr.length < 2
                    || !strArr[0].startsWith("-")
                    || strArr[0].length() < 2
                    || strArr[1].isBlank()) {
                throw new IllegalArgumentException(String.format(
                        "\"%s\" does not match the template \"-key=value\"", s
                ));
            }
            String replace = strArr[0].replaceFirst("-", "");
            values.put(replace, strArr[1]);
        }
    }

    public static CSVReader of(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Usage java -jar CSVReader.java -path=PATH_FILE.CSV "
                            + "-delimiter=\"SEPARATOR\" "
                            + "-out=\"stdout\" or PATH_FILE.CSV "
                            + "-filter=COLUMN_NAMES"
            );
        }
        CSVReader reader = new CSVReader();
        reader.parse(args);
        return reader;
    }
}
