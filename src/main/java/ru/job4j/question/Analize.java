package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, String>  prev = new HashMap<Integer, String>();
        Map<Integer, String>  cur = new HashMap<Integer, String>();
        prev = previous.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName)
                );
        cur = current.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName)
                );
        for (Integer i : cur.keySet()) {
            if (!prev.containsKey(i)) {
                rsl.setAdded(rsl.getAdded() + 1);
            }
            if (!cur.get(i).equals(prev.get(i)) && prev.containsKey(i)) {
                rsl.setChanged(rsl.getChanged() + 1);
            }
        }
        for (Integer j : prev.keySet()) {
            if (!cur.containsKey(j)) {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        }
        return rsl;
    }
}
