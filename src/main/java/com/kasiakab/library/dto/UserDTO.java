package com.kasiakab.library.dto;

import com.kasiakab.library.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
