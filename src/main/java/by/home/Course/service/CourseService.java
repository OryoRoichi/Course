package by.home.Course.service;

import by.home.Course.entity.Course;
import by.home.Course.entity.User;
import by.home.Course.entity.dto.CourseDto;
import by.home.Course.entity.mapper.CourseMapper;
import by.home.Course.exceptions.AuthenticationErrorException;
import by.home.Course.exceptions.CourseNotFoundException;
import by.home.Course.exceptions.UserNotFoundException;
import by.home.Course.repository.CourseRepository;
import by.home.Course.repository.LessonRepository;
import by.home.Course.repository.UserRepository;
import by.home.Course.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {
    CourseRepository courseRepository;

    CourseMapper courseMapper;
    LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Transactional
    public CourseDto createCource(CourseDto request) {
        Course courseToSave = courseMapper.toEntity(request);
        User currentUser = SecurityUtil.getCurrentUser().orElseThrow(()-> new AuthenticationErrorException());
        courseToSave.setMentor(currentUser);
        courseRepository.save(courseToSave);
        return courseMapper.ToDto(courseToSave);
    }

    @Transactional
    public String addStudent(Long studentId,Long courseId) {
        User userToSave = userRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException(studentId));
        Course courseToSet = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        userToSave.getCourse().add(courseToSet);
        courseToSet.setUser(userToSave);
        courseRepository.save(courseToSet);
        userRepository.save(userToSave);
        return "Пользователь успешно добавлен";
    }

    public CourseDto viewCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id));
        return courseMapper.ToDto(course);
    }
    @Transactional
    public String deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
        return String.format("Курс с id = %d был успешно удален", courseId);
    }
}
