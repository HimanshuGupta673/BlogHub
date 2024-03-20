package com.learner.payLoad;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userName;
    private String password;
}
