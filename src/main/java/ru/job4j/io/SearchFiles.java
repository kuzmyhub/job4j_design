package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private Predicate<Path> pred;

    private List<Path> list = new ArrayList<>();

    public SearchFiles(Predicate<Path> pred) {
        this.pred = pred;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pred.test(file)) {
            list.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return list;
    }
}
