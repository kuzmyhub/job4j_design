package ru.job4j.serialization.json;

import java.util.Arrays;

public class Site {
    private final boolean isWorking;
    private final int visitors;
    private final URLAddress urlAddress;
    private final String[] themes;

    public Site(boolean isWorking, int visitors,
                URLAddress urlAddress, String[] themes) {
        this.isWorking = isWorking;
        this.visitors = visitors;
        this.urlAddress = urlAddress;
        this.themes = themes;
    }

    @Override
    public String toString() {
        return "Site{" +
                "isWorking=" + isWorking +
                ", visitors=" + visitors +
                ", urlAddress=" + urlAddress +
                ", themes=" + Arrays.toString(themes) +
                '}';
    }
}
