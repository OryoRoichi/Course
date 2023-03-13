package by.home.Cource.service;

import by.home.Cource.entity.Authority;
import by.home.Cource.entity.User;
import by.home.Cource.entity.dto.UserDto;
import by.home.Cource.entity.mapper.UserMapper;
import by.home.Cource.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;
    BCryptPasswordEncoder encoder;


    private User saveUser(User userToSave) {
        User user = userRepository.save(userToSave);
        return user;
    }
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User userToSave = userMapper.toEntity(userDto);
        userToSave.setAuthorities(userDto
                .getRoles()
                .stream()
                .map(role -> Authority.builder()
                        .authority(role)
                        .orgUser(userToSave)
                        .build())
                .collect(Collectors.toList()));
        UserDto resultDto = userMapper.ToDto(saveUser(userToSave));
        return resultDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(()-> new UsernameNotFoundException("не верное имя пользователя или пароль"));
    }
}
