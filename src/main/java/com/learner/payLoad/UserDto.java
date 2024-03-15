package com.learner.payLoad;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "Name must have minimum 4 characterse")
    private String name;

    @Email(message = "Email can't be empty")
    private String email;

    @NotEmpty
    @Size(min = 5,max = 10,message = "Password must be in between 5 to 10 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{5,10}$", message = "Password must include numbers, characters, special characters, capital and small characters")
    private String password;

    @NotEmpty
    private String about;
}
