package by.home.Course.service;

import by.home.Course.entity.Course;
import by.home.Course.entity.dto.CourseDto;
import by.home.Course.entity.mapper.CourseMapper;
import by.home.Course.repository.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {
    CourseRepository courseRepository;

    CourseMapper courseMapper;

    public CourseDto createCource(CourseDto request) {
        Course courseToSave = courseMapper.toEntity(request);
        courseRepository.save(courseToSave);
        return courseMapper.ToDto(courseToSave);
    }
}
