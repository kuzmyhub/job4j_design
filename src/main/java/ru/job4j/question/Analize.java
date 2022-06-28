package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl;
        Map<Integer, String>  prevMap = previous.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName)
                );
        Map<Integer, String>  curMap = current.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName)
                );
        int prevSize = prevMap.size();
        int curSize = curMap.size();

        if (previous.equals(current)) {
            rsl = new Info(0, 0, 0);
        } else {
            Map<Integer, String> valueMap = prevMap;
            prevMap.remove(current.stream()
                    .map(x -> x.getId())
                    .collect(Collectors.toSet())); // удалённые в current
            curMap.remove(current.stream()
                    .map(x -> x.getId())
                    .collect(Collectors.toSet())); // добавленные в currnet

        }

        for (Integer i : curMap.keySet()) {
            if (!prevMap.containsKey(i)) {
                rsl.setAdded(rsl.getAdded() + 1);
            }
            if (!curMap.get(i).equals(prevMap.get(i))
                    && prevMap.containsKey(i)) {
                rsl.setChanged(rsl.getChanged() + 1);
            }
        }
        for (Integer j : prevMap.keySet()) {
            if (!curMap.containsKey(j)) {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        }
        return rsl;
    }
}
