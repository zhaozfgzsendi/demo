package com.example.demo.model;

import lombok.Data;

@Data
public class UserInfo {

    private Integer id;

    private String accountId;

    private String name;

    private String token;

    private Long gmtCreate;

    private Long gmtModified;
}
