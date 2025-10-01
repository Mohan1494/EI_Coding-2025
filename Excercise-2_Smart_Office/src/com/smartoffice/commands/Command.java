package com.smartoffice.commands;

public interface Command {
    void execute();
    default boolean isConfigCommand() {
        return false;
    }
}
