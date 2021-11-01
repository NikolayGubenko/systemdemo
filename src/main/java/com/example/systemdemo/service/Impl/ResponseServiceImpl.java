package com.example.systemdemo.service.Impl;

import com.example.systemdemo.model.SimpleResponse;
import com.example.systemdemo.repository.ResponseRepository;
import com.example.systemdemo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;

    @Override
    public SimpleResponse saveResponse(String response) {
        return this.responseRepository.save(new SimpleResponse(response));
    }

    @Override
    public List<SimpleResponse> findAllResponses() {
        return this.responseRepository.findAll();
    }

    @Override
    public boolean checkLength(String response) {
        return response.getBytes().length == 40;
    }
}
