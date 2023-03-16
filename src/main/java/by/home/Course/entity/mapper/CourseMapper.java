package by.home.Course.entity.mapper;

import by.home.Course.entity.Course;
import by.home.Course.entity.dto.CourseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDto courceDto);

    CourseDto ToDto(Course course);
}
