package com.avasthi.praakhya.learning.spring.pojo;

import lombok.Builder;
import lombok.Data;

@Data       //hash, equals, setters, getters
@Builder    //builder interface: customisable constructor
public class User{
    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String email;
}
