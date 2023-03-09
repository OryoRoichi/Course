package by.home.Cource.entity.mapper;

import by.home.Cource.entity.HomeWork;
import by.home.Cource.entity.Lesson;
import by.home.Cource.entity.dto.HomeWorkDto;
import by.home.Cource.entity.dto.LessonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HomeWorkMapper {
    HomeWork toEntity(HomeWorkDto homeWorkDto);
    HomeWorkDto ToDto(HomeWork homeWork);
}
