package com.oleg.nee.somerestapiproject.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
}
