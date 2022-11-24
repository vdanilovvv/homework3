package ru.otus.petstore;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Data
@Builder
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
public class UserDTO {
    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String password;
    private String phone;
    private long userStatus;
    private String username;
}
