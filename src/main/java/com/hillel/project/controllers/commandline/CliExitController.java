package com.hillel.project.controllers.commandline;

import com.hillel.project.controllers.ExitController;
import com.hillel.project.views.commandline.CliSimpleAskView;

public class CliExitController implements ExitController {
    @Override
    public boolean start() {
        CliSimpleAskView simpleAskView = new CliSimpleAskView("Do you want to exit?");
        return simpleAskView.ask();
    }
}
