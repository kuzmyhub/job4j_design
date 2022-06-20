package ru.job4j.map;

import java.util.Date;
import java.util.*;
import java.util.Calendar;

public class User {

    private String name;

    int children;

    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        User user1 = new User("Ivan", 1, new GregorianCalendar(2020, 0, 25, 10, 10, 10));
        User user2 = new User("Ivan", 1, new GregorianCalendar(2020, 0, 25, 10, 10, 10));
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        HashMap<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);

        System.out.println(500);
        System.out.println(500 >> 2);
        System.out.println(500 << 4);
        System.out.println((binary(500)));
        System.out.println((binary(125)));
        System.out.println((binary(8000)));
        System.out.println((binary(342)));
        System.out.println();
        int i = 1500;
        System.out.println(Integer.hashCode(i));
        System.out.println(binary(Integer.hashCode(i)));
        System.out.println(Integer.hashCode(i) >>> 16);
        System.out.println(binary(Integer.hashCode(i) >>> 16));
        System.out.println(Integer.hashCode(i) ^ (Integer.hashCode(i) >>> 16));
        System.out.println(binary(Integer.hashCode(i) ^ (Integer.hashCode(i) >>> 16)));
        System.out.println((16 - 1) & (Integer.hashCode(i) ^ (Integer.hashCode(i) >>> 16)));
    }
}
