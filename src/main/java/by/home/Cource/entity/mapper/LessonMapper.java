package by.home.Cource.entity.mapper;

import by.home.Cource.entity.Lesson;
import by.home.Cource.entity.User;
import by.home.Cource.entity.dto.LessonDto;
import by.home.Cource.entity.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    Lesson toEntity(LessonDto lessonDto);
    LessonDto ToDto(Lesson lesson);
}
