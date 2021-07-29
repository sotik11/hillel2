package com.hillel.project.views.commandline;

import lombok.AllArgsConstructor;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Scanner;

@AllArgsConstructor
public class CliSimpleValuableAskView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int REPEAT_COUNT = 3;

    private final String message;

    public String ask() {
        for (int i = 0; i < REPEAT_COUNT + 1; i++) {
            System.out.println(message);
            return SCANNER.nextLine();
        }

        throw new UncheckedIOException(
                new IOException("Cant read your answer more then 3 times, program close"));
    }
}
