package com.example.systemdemo.service.Impl;

import com.example.systemdemo.model.SimpleCommand;
import com.example.systemdemo.model.State;
import com.example.systemdemo.repository.CommandRepository;
import com.example.systemdemo.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandServiceImpl implements CommandService {

    private final CommandRepository commandRepository;

    @Override
    public List<SimpleCommand> findAllCommands() {
        return this.commandRepository.findAll();
    }

    @Override
    public SimpleCommand addCommand(String command) {
        SimpleCommand newCommand = new SimpleCommand(command, State.NEW);
        return this.commandRepository.save(newCommand);
    }

    @Override
    public boolean checkResponse(String response) {
        List<SimpleCommand> commands = this.findAllCommands();
        boolean success = false;
        for (SimpleCommand checkedCommand : commands) {
            boolean checkFirst = checkArrays(0, 4, checkedCommand.getCommand()
                    .getBytes(), response.getBytes());
            boolean checkLast = checkArrays(35, 40, checkedCommand.getCommand()
                    .getBytes(), response.getBytes());
            if ((checkFirst && checkLast) && (checkedCommand.getState() != State.LINKED)) {
                checkedCommand.setState(State.LINKED);
                this.commandRepository.save(checkedCommand);
                success = true;
            }
        }
        return success;
    }

    @Async("threadPoolTaskExecutor")
    @Override
    public void checkCommand(String command) throws InterruptedException {
        Thread.sleep(30000);
        SimpleCommand checkedCommand = this.commandRepository.findByCommand(command);
        if (checkedCommand.getState() == State.NEW) {
            checkedCommand.setState(State.FAILED);
            this.commandRepository.save(checkedCommand);
        }
    }

    private boolean checkArrays(int start, int end, byte[] array1, byte[] array2) {
        return Arrays.equals(Arrays.copyOfRange(array1, start, end), Arrays.copyOfRange(array2, start, end));
    }

   }
