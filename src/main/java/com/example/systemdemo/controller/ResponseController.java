package com.example.systemdemo.controller;

import com.example.systemdemo.model.SimpleResponse;
import com.example.systemdemo.service.CommandService;
import com.example.systemdemo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/response")
public class ResponseController {

    private final CommandService commandService;
    private final ResponseService responseService;

    @GetMapping
    public List<SimpleResponse> getAll() {
        return this.responseService.findAllResponses();
    }

    @PostMapping("/{response}")
    public String addCommand(@PathVariable String response) {
        this.responseService.saveResponse(response);
        String result = "Command not found";
        if (this.responseService.checkLength(response)) {
            if (this.commandService.checkResponse(response)) {
                result = "Command linked";
            }
        }
        return result;
    }

}
