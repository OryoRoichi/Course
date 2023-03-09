package by.home.Cource.entity.mapper;

import by.home.Cource.entity.User;
import by.home.Cource.entity.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);
    UserDto ToDto(User user);

}
