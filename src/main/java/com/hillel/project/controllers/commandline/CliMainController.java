package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.LogoutController;
import com.hillel.project.controllers.MainController;
import com.hillel.project.controllers.Controller;
import com.hillel.project.entities.User;
import com.hillel.project.views.commandline.CliControllerRunnableMenuView;

public class CliMainController implements MainController {

    @Override
    public void start() {
        while (true) {
            CliLoginController cliLoginController = new CliLoginController();
            User user = cliLoginController.start();

            Controller controller = new CliControllerRunnableMenuView()
                    .withOptions("Profile", new CliProfileController())
                    .withOptions("New message", new CliMessageSenderController())
                    // TODO: add Messages, Settings, Exit, LogOut
                    .runAndGetNextController();

            if (controller instanceof LogoutController) {
                boolean logout = ((LogoutController) controller).start();
                if (logout) {
                    System.out.println("Byy!");
                    continue;
                }
            }
//            if (controller instanceof ExitController) {
//                boolean exit = ((ExitController) controller).start();
//                if (exit) {
//                    System.out.println("Byy!");
//                    return;
//                }
//            }
        }




    }
}
