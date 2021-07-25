package com.hillel.project;

import com.hillel.project.controllers.ControllersFactory;
import com.hillel.project.controllers.MainController;

public class App {
    public static void main(String[] args) {
        ControllersFactory controllersFactory = new ControllersFactory(
                ControllersFactory.Type.COMMAND_LINE_INTERFACE);

        MainController mainController = controllersFactory.getMainController();
        mainController.start();
    }
}
