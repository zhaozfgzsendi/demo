package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private Integer id;

    private String login;

    private String name;

    private String bio;
}
