package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl;
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String>  prevMap = previous.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName)
                );
        Map<Integer, String>  curMap = current.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName)
                );
        if (!previous.equals(current)) {
            for (Integer i : curMap.keySet()) {
                if (!prevMap.containsKey(i)) {
                    added++;
                }
                if (!curMap.get(i).equals(prevMap.get(i))
                        && prevMap.containsKey(i)) {
                    changed++;
                }
            }
            for (Integer j : prevMap.keySet()) {
                if (!curMap.containsKey(j)) {
                    deleted++;
                }
            }
        }
        rsl = new Info(added, changed, deleted);
        return rsl;
    }
}
