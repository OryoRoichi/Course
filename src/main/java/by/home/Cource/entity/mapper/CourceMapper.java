package by.home.Cource.entity.mapper;

import by.home.Cource.entity.Cource;
import by.home.Cource.entity.User;
import by.home.Cource.entity.dto.CourceDto;
import by.home.Cource.entity.dto.UserDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CourceMapper {
    Cource toEntity(CourceDto courceDto);
    CourceDto ToDto(Cource cource);
}
