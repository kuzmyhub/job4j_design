package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.Path;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (Path p : sources) {
                zip.putNextEntry(new ZipEntry(p.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(p.toString())
                )) {
                    zip.write((out.readAllBytes()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source)
            )) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        String[] argsArr = {
                argsName.get("d"),
                argsName.get("e"),
                argsName.get("o")
        };
        Search.validation(argsArr);
        Path path = Paths.get(argsName.get("d"));
        List<Path> list = Search.search(path,
                x -> !x.toFile()
                        .getName()
                        .endsWith(argsName.get("e")));
        zip.packFiles(list, new File(argsName.get("o")));
    }
}
