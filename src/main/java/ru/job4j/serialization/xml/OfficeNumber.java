package ru.job4j.serialization.xml;

public class OfficeNumber {
    private final String number;

    public OfficeNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OfficeNumber{"
                + "number='" + number + '\''
                + '}';
    }
}
