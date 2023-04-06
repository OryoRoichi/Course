package by.home.Course.service;

import by.home.Course.entity.Course;
import by.home.Course.entity.User;
import by.home.Course.entity.dto.CourseDto;
import by.home.Course.entity.mapper.CourseMapper;
import by.home.Course.exceptions.CourseNotFoundException;
import by.home.Course.exceptions.UserNotFoundException;
import by.home.Course.repository.CourseRepository;
import by.home.Course.repository.LessonRepository;
import by.home.Course.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {
    CourseRepository courseRepository;

    CourseMapper courseMapper;
    LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public CourseDto createCource(CourseDto request) {
        Course courseToSave = courseMapper.toEntity(request);
        courseRepository.save(courseToSave);
        return courseMapper.ToDto(courseToSave);
    }

    public String addStudent(Long studentId,CourseDto request) {
        User userToSave = userRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException(studentId));
        Course courseToSet = courseMapper.toEntity(request);
        userToSave.getCourse().add(courseToSet);
        userRepository.save(userToSave);
        return "Пользователь успешно добавлен";
    }

    public CourseDto viewCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id));
        return courseMapper.ToDto(course);
    }
}
