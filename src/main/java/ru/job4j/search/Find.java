package ru.job4j.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Find {
    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validation(argsName, args);
        Predicate<Path> predicate = null;
        if (argsName.get("t").equals("name")) {
            predicate = p -> p.toFile()
                    .getName()
                    .contains(argsName.get("n"));
        } else if (argsName.get("t").equals("mask")) {
            FileSystem fs = FileSystems.getDefault();
            PathMatcher matcher = fs.getPathMatcher("glob:" + argsName.get("n"));
            predicate = p -> matcher.matches(p.getFileName());
        }
        List<Path> list = Search.search(Paths.get(argsName.get("d")), predicate);
         try (BufferedWriter out = new BufferedWriter(
                 new PrintWriter(argsName.get("o"))
         )) {
             for (Path p : list) {
                 out.write(p.toString() + System.lineSeparator());
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public static void validation(ArgsName argsName, String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Usage java -jar Find.java"
                    + "-d=PATH_DIRECTORY -n=FILE_NAME.EXTENSION or MASK "
                    + "-t=\"name\" or \"mask\" -o=PATH_FILE.TXT");
        }

        File dir = new File(argsName.get("d"));
        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format(
                    "%s not exist", dir.getAbsolutePath()
            ));
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "%s not directory", dir.getAbsolutePath()
            ));
        }

        if (!argsName.get("n").contains(".") || (argsName.get("t").equals("name")
                && (argsName.get("n").contains("*") || argsName.get("n").contains("?")))
                || (argsName.get("t").equals("mask")
                && (!argsName.get("n").contains("*") || !argsName.get("n").contains("?")))) {
            throw new IllegalArgumentException("Usage -n=FILE_NAME.EXTENSION"
                    + "with -t=\"name\" or -n=MASK with -t=\"mask\"");
        }

        File file = new File(argsName.get("o"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format(
                    "%s not exist", file.getAbsolutePath()
            ));
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "%s not file", file.getAbsolutePath()
            ));
        }
    }
}
