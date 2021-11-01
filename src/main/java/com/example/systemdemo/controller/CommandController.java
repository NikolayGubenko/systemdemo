package com.example.systemdemo.controller;

import com.example.systemdemo.model.SimpleCommand;
import com.example.systemdemo.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/command")
public class CommandController {

    private final CommandService commandService;

    @GetMapping
    public List<SimpleCommand> getAll() {
        return this.commandService.findAllCommands();
    }

    @PostMapping("/{command}")
    public SimpleCommand addCommand(@PathVariable String command) throws InterruptedException {
        this.commandService.checkCommand(command);
        return this.commandService.addCommand(command);
    }

}
