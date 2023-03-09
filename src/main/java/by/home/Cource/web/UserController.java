package by.home.Cource.web;

import by.home.Cource.entity.dto.UserDto;
import by.home.Cource.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("/auth")
public class UserController {
    UserService userService;
    @PostMapping("/reg")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.createUser(user));
    }
}
