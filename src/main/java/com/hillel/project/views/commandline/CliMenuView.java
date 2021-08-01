package com.hillel.project.views.commandline;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CliMenuView {
    private final Map<Integer, String> options = new TreeMap<>();

    public CliMenuView withOption(Integer key, String message) {
        options.put(key, message);
        return this;
    }

    public Integer run() {
        Scanner scanner = new Scanner(System.in);
        show();
        return scanner.nextInt();
    }

    private void show() {
        for (Map.Entry<Integer, String> entry : options.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
