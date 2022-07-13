package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void dropFirstCase() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(
                source.getAbsolutePath(),
                target.getAbsolutePath()
        );
        StringBuilder str = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new FileReader(target)
        )) {
            in.lines()
                    .forEach(x -> str.append(x + System.lineSeparator()));
        }
        assertThat(str.toString(), is("10:57:01;10:59:01"
                + System.lineSeparator()
                + "11:01:02;11:02:02"
                + System.lineSeparator()));
    }

    @Test
    public void dropSecondCase() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(
                source.getAbsolutePath(),
                target.getAbsolutePath()
        );
        StringBuilder str = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new FileReader(target)
        )) {
            in.lines()
                    .forEach(x -> str.append(x + System.lineSeparator()));
        }
        assertThat(str.toString(), is("10:57:01;11:02:02"
                + System.lineSeparator()));
    }

    @Test
    public void dropThirdCase() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("500 11:55:01");
            out.println("500 12:45:01");
            out.println("200 12:45:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(
                source.getAbsolutePath(),
                target.getAbsolutePath()
        );
        StringBuilder str = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new FileReader(target)
        )) {
           in.lines()
                   .forEach(x -> str.append(x + System.lineSeparator()));
           assertThat(str.toString(), is("11:55:01;12:45:02"
                   + System.lineSeparator()));
        }
    }
}