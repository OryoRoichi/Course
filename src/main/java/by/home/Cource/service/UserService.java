package by.home.Cource.service;

import by.home.Cource.entity.User;
import by.home.Cource.entity.dto.UserDto;
import by.home.Cource.entity.mapper.UserMapper;
import by.home.Cource.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;


    public UserDto createUser(UserDto user) {
        User userToSave = userMapper.toEntity(user);
        userRepository.save(userToSave);
        return userMapper.ToDto(userToSave);
    }
}
