package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4L;
        float f = 0.5F;
        double d = 0.6D;
        char c = 'c';
        boolean isTrue = true;
        LOG.debug(
                "Primitive types: {}, {}, {}, {}, {}, {}, {}, {}.",
                b, s, i, l, f, d, c, isTrue
        );
    }
}
