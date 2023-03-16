package by.home.Course.entity.mapper;

import by.home.Course.entity.Lesson;
import by.home.Course.entity.dto.LessonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    Lesson toEntity(LessonDto lessonDto);

    LessonDto ToDto(Lesson lesson);
}
