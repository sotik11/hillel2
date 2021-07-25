package com.hillel.project.controllers;

import com.hillel.project.controllers.commandline.CliLoginController;
import com.hillel.project.controllers.commandline.CliMainController;
import lombok.Getter;

@Getter
public class ControllersFactory {

    public enum Type {
        COMMAND_LINE_INTERFACE
    }

    private final MainController mainController;
    private final LoginController loginController;

    public ControllersFactory(Type type) {
        switch (type) {
            case COMMAND_LINE_INTERFACE:
                mainController = new CliMainController();
                loginController = new CliLoginController();
                break;

            default:
                throw new IllegalArgumentException("Unsupported TYPE=" + type.name());
        }
    }
}
