package by.home.Course.entity.mapper;

import by.home.Course.entity.User;
import by.home.Course.entity.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

}
