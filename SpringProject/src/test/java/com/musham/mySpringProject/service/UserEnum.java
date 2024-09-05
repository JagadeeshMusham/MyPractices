package com.musham.mySpringProject.service;

public enum UserEnum {
    TEST("test"),
    TEST1("test11"),
    TEST10("test10");

    private final String name;

    UserEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
