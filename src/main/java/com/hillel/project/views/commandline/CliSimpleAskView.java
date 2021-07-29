package com.hillel.project.views.commandline;

import lombok.AllArgsConstructor;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Scanner;

@AllArgsConstructor
public class CliSimpleAskView {
    private static final Scanner SCANNER = new Scanner(System.in);
    static final String[] TRUE_ANSWER = {"y", "Y", "yes", "YES", "1", "+"};
    private static final String[] FALSE_ANSWER = {"n", "N", "no", "NO", "0", "-"};
    private static final int REPEAT_COUNT = 3;

    private final String message;

    public boolean ask() {
        for (int i = 0; i < REPEAT_COUNT + 1; i++) {
            System.out.println(message);
            String answer = SCANNER.nextLine();
            if (isTrue(answer)) {
                return true;
            }
            if (isFalse(answer)) {
                return false;
            }
            System.out.println("Cant read your answer, try again");
        }

        throw new UncheckedIOException(
                new IOException("Cant read your answer more then 3 times, program close"));
    }

    private boolean isTrue(String answer) {
        return isContains(TRUE_ANSWER, answer);
    }

    private boolean isFalse(String answer) {
        return isContains(FALSE_ANSWER, answer);
    }

    private static boolean isContains(String[] expectedAnswer, String userAnswer) {
        return Arrays.asList(expectedAnswer).contains(userAnswer);
    }
}
