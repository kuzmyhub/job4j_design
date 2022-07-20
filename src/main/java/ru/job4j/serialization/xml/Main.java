package ru.job4j.serialization.xml;

public class Main {
    public static void main(String[] args) {
        final Office office = new Office(false, 2,
                new OfficeNumber("I-110"),
                new String[] {"Zhenya, Sasha"});
    }
}
