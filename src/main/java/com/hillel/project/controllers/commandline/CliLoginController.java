package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.LoginController;
import com.hillel.project.entities.User;
import com.hillel.project.exceptions.IncorrectPassException;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.utils.RetryHelper;
import com.hillel.project.views.commandline.CliSimpleAskView;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;
import java.util.Optional;

public class CliLoginController implements LoginController {

    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private static final int RETRY_COUNT = 3;

    @Override
    public User start() {
        CliSimpleAskView registrationAsk = new CliSimpleAskView("Do you have an account?");
        RetryHelper<User> retryHelper = new RetryHelper(RETRY_COUNT);
        if (registrationAsk.ask()) {
            Optional<User> userContainer = retryHelper.run(() -> {
                try {
                    return login();
                } catch (NotFoundException e) {
                    throw new RuntimeException("User with this login not found, please try again");
                } catch (IncorrectPassException e) {
                    throw new RuntimeException("Incorrect password");
                }
            });
            if (userContainer.isPresent()) {
                return userContainer.get();
            } else {
                throw new RuntimeException("User is empty!");
            }

        } else {
            return register();
        }
    }

    private User login() throws NotFoundException, IncorrectPassException {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter your login, please");
        String login = loginAsk.ask();

        User user = USER_REPOSITORY.findByLogin(login);

        CliSimpleValuableAskView passAsk = new CliSimpleValuableAskView("Enter your pass, please");
        String pass = passAsk.ask();

        if (pass.equals(user.getPass())) {
            return user;
        } else {
            throw new IncorrectPassException();
        }
    }

    private User register() {
        return null; // TODO:
    }
}
