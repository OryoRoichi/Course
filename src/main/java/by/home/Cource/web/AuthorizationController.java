package by.home.Cource.web;

import by.home.Cource.entity.dto.UserDto;
import by.home.Cource.entity.enm.Role;
import by.home.Cource.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("/auth")
public class AuthorizationController {
    UserService userService;
    @PostMapping("/reg")
    public ResponseEntity<UserDto> userRegistration(@RequestBody UserDto userDto){
        userDto.setRoles(List.of(Role.ROlE_STUDENT));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }
}
