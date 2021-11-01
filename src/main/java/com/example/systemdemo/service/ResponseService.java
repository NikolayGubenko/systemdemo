package com.example.systemdemo.service;

import com.example.systemdemo.model.SimpleResponse;

import java.util.List;

public interface ResponseService {

    SimpleResponse saveResponse(String response);

    List<SimpleResponse> findAllResponses();

    boolean checkLength(String response);

}
