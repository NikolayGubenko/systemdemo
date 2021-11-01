package com.example.systemdemo.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "responses")
public class SimpleResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "response")
    private String response;

    public SimpleResponse(String response) {
        this.response = response;
    }
}
