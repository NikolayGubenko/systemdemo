package com.example.systemdemo.repository;

import com.example.systemdemo.model.SimpleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<SimpleResponse, Long> {
}
