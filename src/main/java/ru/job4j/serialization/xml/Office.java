package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Office {
    private final boolean isEmpty;
    private final int staff;
    private final OfficeNumber officeNumber;
    private final String[] whose;

    public Office(boolean isEmpty, int staff,
                  OfficeNumber officeNumber, String[] whose) {
        this.isEmpty = isEmpty;
        this.staff = staff;
        this.officeNumber = officeNumber;
        this.whose = whose;
    }

    @Override
    public String toString() {
        return "Office{"
                + "isEmpty=" + isEmpty
                + ", staff=" + staff
                + ", officeNumber=" + officeNumber
                + ", who=" + Arrays.toString(whose)
                + '}';
    }
}
