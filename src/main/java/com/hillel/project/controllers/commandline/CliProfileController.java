package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.ProfileController;
import com.hillel.project.entities.User;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.exceptions.NotValidLoginException;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.views.commandline.CliSimpleAskView;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;

public class CliProfileController implements ProfileController {
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();

    public boolean start() {
        {
            CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter the login of the user which we want to find:");
            String login = loginAsk.ask();

            User user = null;
            try {
                user = USER_REPOSITORY.findByLogin(login);
            } catch (NotFoundException e) {
                try {
                    throw new NotValidLoginException("User with this login not found, please try again");
                } catch (NotValidLoginException notValidLoginException) {
                    notValidLoginException.printStackTrace();
                }
            }
            if (user != null) {
                System.out.println("User's login is: " + user.getLogin());
            }
            if (user != null) {
                System.out.println("User's pass is: " + user.getPass());
            }

            CliSimpleAskView findAnotherUser = new CliSimpleAskView("Do you want to find another user?");
            if (findAnotherUser.ask()) {
                start();
            }
            return true;
        }
    }
}



