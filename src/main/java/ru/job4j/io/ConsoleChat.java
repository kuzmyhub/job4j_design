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

    public void run() throws IOException {
        List<String> bot = readPhrases();
        Random random = new Random();
        List<String> conversation = new ArrayList<>();
        int botQuantity = bot.size();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        String trigger = "продолжить";
        while (!trigger.equals("закончить")) {
            String phrase = in.readLine();
            conversation.add(phrase);
            if (phrase.equals("продолжить")
                    || phrase.equals("стоп")
                    || phrase.equals("закончить")) {
                trigger = phrase;
            }
            if (!trigger.equals("стоп")
                    && !trigger.equals("закончить")) {
                int index = random.
                        ints(0, botQuantity)
                        .findFirst()
                        .getAsInt();
                conversation.add(bot.get(index));
                System.out.println(bot.get(index));
            }
            if (trigger.equals("закончить")) {
                break;
            }
        }
        saveLog(conversation);
    }

    private List<String> readPhrases() {
        List<String> botPhrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(
                        "./ConsoleChat/botAnswers.txt",
                        Charset.forName("WINDOWS-1251")
                ))) {
            in.lines().forEach(botPhrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botPhrases;
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

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat(
                "./ConsoleChat/chatHistory.txt",
                "./ConsoleChat/botAnswers.txt"
        );
        cc.run();
    }
}
