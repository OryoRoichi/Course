package by.home.Course.service;

import by.home.Course.entity.Lesson;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.stateRequests.StateRequestDto;
import by.home.Course.entity.mapper.LessonMapper;
import by.home.Course.exceptions.CourseNotFoundException;
import by.home.Course.repository.CourseRepository;
import by.home.Course.repository.LessonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.function.Function;

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


    public Function<StateRequestDto<LessonDto>, LessonDto> createLesson() {
        return stageRequest -> {
            return addLesson(stageRequest.getRequest());
        };
    }

    public LessonDto addLesson(LessonDto request) {
        return lessonMapper.ToDto(lessonToSaveMaker(request.getCourseId(), request));
    }
}
