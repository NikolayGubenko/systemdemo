package com.example.systemdemo.service;

import com.example.systemdemo.model.SimpleCommand;

import java.util.List;

public interface CommandService {

    List<SimpleCommand> findAllCommands();

    SimpleCommand addCommand(String command);

    boolean checkResponse(String response);

    void checkCommand(String command) throws InterruptedException;

}
