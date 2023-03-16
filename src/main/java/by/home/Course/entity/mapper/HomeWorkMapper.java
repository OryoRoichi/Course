package by.home.Course.entity.mapper;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.dto.HomeWorkDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HomeWorkMapper {
    HomeWork toEntity(HomeWorkDto homeWorkDto);

    HomeWorkDto ToDto(HomeWork homeWork);
}
