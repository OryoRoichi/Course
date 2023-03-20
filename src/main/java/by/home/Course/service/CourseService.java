package by.home.Course.service;

import by.home.Course.entity.Course;
import by.home.Course.entity.HomeWork;
import by.home.Course.entity.User;
import by.home.Course.entity.dto.CourseDto;
import by.home.Course.entity.dto.HomeWorkViewDto;
import by.home.Course.entity.mapper.CourseMapper;
import by.home.Course.exceptions.LessonNotFoundException;
import by.home.Course.repository.CourseRepository;
import by.home.Course.repository.LessonRepository;
import by.home.Course.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {
    CourseRepository courseRepository;

    CourseMapper courseMapper;
    LessonRepository lessonRepository;
    UserRepository userRepository;

    public CourseDto createCource(CourseDto request) {
        Course courseToSave = courseMapper.toEntity(request);
        courseRepository.save(courseToSave);
        return courseMapper.ToDto(courseToSave);
    }

    public HomeWorkViewDto checkHomeWork(Long lessonId,Long courseId) {
        List<HomeWork> homeWorkList = lessonRepository
                .findById(lessonId).orElseThrow(()-> new LessonNotFoundException(lessonId))
                .getHomeWork();
      //  List<User> userList = userRepository.findUserByCourseId(courseId);
        return null;
    }

}
