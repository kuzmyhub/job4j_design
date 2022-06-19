package ru.job4j;

import java.util.HashMap;

public class Bits {
    public static void main(String[] args) {
        int num = 123;
        System.out.println(num);
        System.out.println(hashCode(num));
        int value = 123 >>> 4;
        System.out.println(value);
        System.out.println(hashCode(value));
        int mix = num ^ value;
        System.out.println(mix);
        System.out.println(hashCode(mix));
        int and = value & mix;
        System.out.println(and);
        System.out.println(hashCode(and));
        System.out.println(1 & 15);
        System.out.println(17 & 15);
    }

    public static String hashCode(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }
}
