package com.hillel.project.views.commandline;

import com.hillel.project.controllers.Controller;
import java.util.Map;
import java.util.TreeMap;

public class CliControllerRunnableMenuView {
    private final Map<Integer, Controller> runnableControllers = new TreeMap<>();
    private final CliMenuView cliMenuView = new CliMenuView();
    private int id = 1;

    public CliControllerRunnableMenuView withOptions(String message, Controller controller) {
        runnableControllers.put(id, controller);
        cliMenuView.withOption(id, message);
        id++;
        return this;
    }

    public Controller runAndGetNextController() {
        int id = cliMenuView.run();
        return runnableControllers.get(id);
    }
}
