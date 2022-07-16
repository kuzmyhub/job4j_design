package ru.job4j.io;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> user = readPhrases();
        List<String> bot = new ArrayList<>();
        Random random = new Random();
        List<String> conversation = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(
                        botAnswers,
                        Charset.forName("WINDOWS-1251"
                )))) {
            in.lines().forEach(bot::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int botQuantity = bot.size();
        String trigger = "продолжить";
        int counter = 0;
        while (!trigger.equals("закончить")) {
            conversation.add(user.get(counter));
            if (user.get(counter).equals("продолжить")
                    || user.get(counter).equals("стоп")
                    || user.get(counter).equals("закончить")) {
                trigger = user.get(counter);
            }
            if (!trigger.equals("стоп")
                    && !trigger.equals("закончить")) {
                int index = random.
                        ints(0, botQuantity)
                        .findFirst()
                        .getAsInt();
                conversation.add(bot.get(index));
            }
            if (counter != user.size() - 1) {
                counter++;
            }
        }
        saveLog(conversation);
    }

    private List<String> readPhrases() {
        List<String> userAnswers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader("./ConsoleChat/userPhrases.txt", Charset.forName("WINDOWS-1251"))
        )) {
            in.lines().forEach(userAnswers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userAnswers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "./ConsoleChat/chatHistory.txt",
                "./ConsoleChat/botAnswers.txt"
        );
        cc.run();
    }
}
