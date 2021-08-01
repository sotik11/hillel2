package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.LoginController;
import com.hillel.project.entities.User;
import com.hillel.project.exceptions.IncorrectPassException;
import com.hillel.project.exceptions.NotFoundException;
import com.hillel.project.exceptions.NotValidLoginException;
import com.hillel.project.repositories.UserRepository;
import com.hillel.project.utils.RetryHelper;
import com.hillel.project.views.commandline.CliSimpleAskView;
import com.hillel.project.views.commandline.CliSimpleValuableAskView;
import java.util.Optional;
import java.util.function.Supplier;

public class CliLoginController implements LoginController {

    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private static final int RETRY_COUNT = 3;
    private static final Supplier<User> REGISTRATION_SUPPLIER = () -> {
        try {
            return register();
        } catch (NotValidLoginException | IncorrectPassException e) {
            throw new RuntimeException(e);
        }
    };
    private static final Supplier<User> LOGIN_SUPPLIER = () -> {
        try {
            return login();
        } catch (NotValidLoginException | IncorrectPassException e) {
            throw new RuntimeException(e);
        }
    };

    @Override
    public User start() {
        CliSimpleAskView registrationAsk = new CliSimpleAskView("Do you have an account?");
        RetryHelper<User> retryHelper = new RetryHelper(RETRY_COUNT);
        Supplier<User> supplier = registrationAsk.ask() ? LOGIN_SUPPLIER : REGISTRATION_SUPPLIER;
        Optional<User> userContainer = retryHelper.run(supplier);
        return getUserFromOptional(userContainer);
    }

    protected static User login() throws IncorrectPassException, NotValidLoginException {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter your login, please");
        String login = loginAsk.ask();

        User user;
        try {
            user = USER_REPOSITORY.findByLogin(login);
        } catch (NotFoundException e) {
            throw new NotValidLoginException("User with this login not found, please try again");
        }
        CliSimpleValuableAskView passAsk = new CliSimpleValuableAskView("Enter your pass, please");
        String pass = passAsk.ask();
        if (pass.equals(user.getPass())) {
            return user;
        } else {
            throw new IncorrectPassException("Incorrect password");
        }
    }

    protected static User register() throws IncorrectPassException, NotValidLoginException {
        CliSimpleValuableAskView loginAsk = new CliSimpleValuableAskView("Enter your login, please");
        String login = loginAsk.ask();
        try {
            USER_REPOSITORY.findByLogin(login);
        } catch (NotFoundException e) {
            CliSimpleValuableAskView passAsk = new CliSimpleValuableAskView("Enter your pass, please");
            String pass = passAsk.ask();
            CliSimpleValuableAskView pass2Ask = new CliSimpleValuableAskView("Repeat your pass, please");
            String pass2 = pass2Ask.ask();

            if (pass.equals(pass2)) {
                User user = new User(login, pass);
                USER_REPOSITORY.save(user);
                return user;
            } else {
                throw new IncorrectPassException("Password1 and Password2 are different, please try again");
            }
        }
        throw new NotValidLoginException("User with login: " + login + " already exist");
    }

    private User getUserFromOptional(Optional<User> userContainer) {
        if (userContainer.isPresent()) {
            return userContainer.get();
        } else {
            throw new RuntimeException("User is empty!");
        }
    }
}
