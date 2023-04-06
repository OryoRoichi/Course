package by.home.Course.web;

import by.home.Course.entity.User;
import by.home.Course.entity.dto.UserDto;
import by.home.Course.entity.enums.Role;
import by.home.Course.entity.mapper.UserMapper;
import by.home.Course.security.JwtUtil;
import by.home.Course.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/auth")
public class AuthorizationController {
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    UserMapper userMapper;
    UserService userService;
    PasswordEncoder encoder;

    @PostMapping("/regStudent")
    public ResponseEntity<UserDto> studentRegistration(@RequestBody UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userDto.setRoles(List.of(Role.ROLE_STUDENT));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PostMapping("/regMentor")
    public ResponseEntity<UserDto> mentorRegistration(@RequestBody UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userDto.setRoles(List.of(Role.ROLE_MENTOR));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(user))
                .body(userMapper.toDto(user));
    }
}
