package by.home.Cource.entity.dto;

import by.home.Cource.entity.enm.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    String name;
    String login;

    String password;
    @JsonIgnore
    List<Role> roles;

}
