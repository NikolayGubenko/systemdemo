package com.example.systemdemo.repository;

import com.example.systemdemo.model.SimpleCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<SimpleCommand, Long> {

    SimpleCommand findByCommand(String command);

}
