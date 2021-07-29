package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.LogoutController;
import com.hillel.project.views.commandline.CliSimpleAskView;

public class CliLogoutController implements LogoutController {
    @Override
    public boolean start() {
        CliSimpleAskView logoutAskView = new CliSimpleAskView("Do you want to logout?");
        return logoutAskView.ask();
    }
}
