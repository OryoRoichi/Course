package by.home.Course.service;

import by.home.Course.entity.Authority;
import by.home.Course.entity.User;
import by.home.Course.entity.dto.UserDto;
import by.home.Course.entity.mapper.UserMapper;
import by.home.Course.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;



    private User saveUser(User userToSave) {
        User user = userRepository.save(userToSave);
        return user;
    }
    public UserDto createUser(UserDto userDto) {
        User userToSave = userMapper.toEntity(userDto);
        userToSave.setAuthorities(userDto
                .getRoles()
                .stream()
                .map(role -> Authority.builder()
                        .authority(role)
                        .orgUser(userToSave)
                        .build())
                .collect(Collectors.toList()));
        UserDto resultDto = userMapper.toDto(saveUser(userToSave));
        return resultDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(()-> new UsernameNotFoundException("не верное имя пользователя или пароль"));
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
