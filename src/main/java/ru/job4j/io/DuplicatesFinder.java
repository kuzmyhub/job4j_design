package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("."), duplicatesVisitor);
        Map<FileProperty, List<Path>> map = duplicatesVisitor.getFiles();
        for (FileProperty f : map.keySet()) {
            List<Path> list = map.get(f);
            if (list.size() >= 2) {
                System.out.println(f.getName()
                        + " - "
                        + f.getSize());
                for (Path p : list) {
                    System.out.println(p);
                }
                System.out.println();
            }
        }
    }
}
