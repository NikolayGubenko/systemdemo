package com.example.systemdemo.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commands")
public class SimpleCommand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "command")
    private String command;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    public SimpleCommand(String command, State state) {
        this.command = command;
        this.state = state;
    }
}
