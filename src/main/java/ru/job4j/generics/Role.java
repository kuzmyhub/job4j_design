package ru.job4j.generics;

public class Role extends Base {

    private String userRole;

    public Role(String id, String userRole) {
        super(id);
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
