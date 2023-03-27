package by.home.Course.service;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.Lesson;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.mapper.LessonMapper;
import by.home.Course.exceptions.CourseNotFoundException;
import by.home.Course.exceptions.LessonNotFoundException;
import by.home.Course.repository.CourseRepository;
import by.home.Course.repository.LessonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonService {
    LessonRepository lessonRepository;
    CourseRepository courseRepository;
    LessonMapper lessonMapper;
    private Lesson lessonToSaveMaker(Long courseId, LessonDto request){
        Lesson lessonToSave = lessonMapper.toEntity(request);
        lessonRepository.save(lessonToSave);
        courseRepository.findById(courseId)
                .map(course -> course.getLesson().add(lessonToSave))
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        return lessonToSave;
    }


    public LessonDto addLesson(Long courseId, LessonDto request) {
        return lessonMapper.ToDto(lessonToSaveMaker(courseId,request));
    }





}
