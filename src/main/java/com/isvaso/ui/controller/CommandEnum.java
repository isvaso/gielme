package com.isvaso.ui.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum CommandEnum {
    LIST("L"),
    CREATE("C"),
    SOLVE("S"),
    DELETE("D"),
    BACK("B"),
    QUIT("Q");

    private final String key;

    public static Optional<CommandEnum> getByKey(String input) {
        return Arrays.stream(values())
                .filter(cmd -> cmd.key.equalsIgnoreCase(input))
                .findFirst();
    }

}


