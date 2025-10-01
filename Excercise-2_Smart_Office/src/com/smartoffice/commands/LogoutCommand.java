package com.smartoffice.commands;

public class LogoutCommand implements Command {

    @Override
    public void execute() {
        // Nothing needed here, Main handles logout logic
    }


    public boolean isConfigCommand() {
        return false;
    }
}
