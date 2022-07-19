package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFilterTwoColumns() throws IOException {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected,
                Files.readString(target.toPath()));
    }

    @Test
    public void whenFilterAllColumns() throws IOException {
        String date = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter=name,age,last_name,education"
        });
        Files.writeString(file.toPath(), date);
        String expected = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSourcePathNotExist() throws IOException {
        String date = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=C:\\",
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter=name,age,education"
        });
        Files.writeString(file.toPath(), date);
        CSVReader.handle(argsName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDelimiterNotSymbol() throws IOException {
        String date = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsolutePath(),
                "-delimiter=1",
                "-out=" + target.getAbsolutePath(),
                "-filter=name,age,education"
        });
        Files.writeString(file.toPath(), date);
        CSVReader.handle(argsName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTargetPathNotExist() throws IOException {
        String date = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=C:\\",
                "-filter=name,age,education"
        });
        Files.writeString(file.toPath(), date);
        CSVReader.handle(argsName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSourceColumnsNotContainsFilterColumns() throws IOException {
        String date = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter=ame,ge,ducation"
        });
        Files.writeString(file.toPath(), date);
        CSVReader.handle(argsName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsDontMatchTheTemplate() throws IOException {
        String date = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[] {
                "path=" + file.getAbsolutePath(),
                "delimiter=;",
                "out=" + target.getAbsolutePath(),
                "filter=name,age,education"
        });
        Files.writeString(file.toPath(), date);
        CSVReader.handle(argsName);
    }

}