package com.imooc.enums;

public enum Sex {
    woman(0, "woman"),
    man(1, "man"),
    secret(2, "secret");

    public final Integer type;

    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
