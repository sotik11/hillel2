package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.*;
import com.hillel.project.entities.User;
import com.hillel.project.exceptions.IncorrectPassException;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.exceptions.NotValidLoginException;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.repositories.UserRepositorySerialized;
import com.hillel.project.views.commandline.CliControllerRunnableMenuView;
import com.hillel.project.views.commandline.CliSimpleAskView;

public class CliMainController implements MainController {

    @Override
    public void start() {
        while (true) {
            CliLoginController cliLoginController = new CliLoginController();
            User user = cliLoginController.start();

            Controller controller = new CliControllerRunnableMenuView()
//                    .withOptions("Profile", new CliProfileController())
                    .withOptions("Admin Panel", new CliProfileController())
                    .withOptions("New message", new CliMessageSenderController())
                    .withOptions("Log out", new CliLogoutController())
                    .withOptions("Exit", new CliExitController())
                    // TODO: add Messages, Settings
                    .runAndGetNextController();

            if (controller instanceof LogoutController) {
                boolean logout = ((LogoutController) controller).start();
                if (logout) {
                    System.out.println("Byy!");
                    continue;
                }
            }
            if (controller instanceof ExitController) {
                boolean exit = ((ExitController) controller).start();
                if (exit) {
                    System.out.println("Byy!");
                    ((UserRepositorySerialized) UserRepository.getInstance()).close();
                    return;
                }
            }
            if (controller instanceof ProfileController) {
                        System.out.println("Your login is: " + user.getLogin());
                        System.out.println("Your password is: " + user.getPass() + "\n");
                CliSimpleAskView findUser = new CliSimpleAskView("Do you want to find another user?");
                if (findUser.ask()) {
                boolean profile = ((ProfileController) controller).start();
//                if (profile) {
//                        System.out.println("User's login is: " + profile.getLogin());
//                        System.out.println("User's password is: " + profile.getPass() + "\n");
                    System.out.println("Return to Main Menu\n");
                    new CliLoginController();
                }else{
                    System.out.println("Return to Main Menu\n");
                    new CliLoginController();
                }
                }
            }
        }
    }

