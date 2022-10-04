package me.djtpj.debt;

import lombok.Getter;

public enum Severity {
    LOG("", ""),
    INFO("[INFO]", "white"),
    WARNING("[WARNING]", "yellow"),
    ERROR("[ERROR]", "red");

    @Getter
    private final String tag;

    @Getter
    private final String color;

    Severity(String tag, String color) {
        this.tag = tag;
        this.color = color;
    }
}
