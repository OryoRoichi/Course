package by.home.Course.service;

import by.home.Course.entity.Lesson;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.mapper.LessonMapper;
import by.home.Course.repository.LessonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LessonService {
    LessonRepository lessonRepository;
    LessonMapper lessonMapper;

    public LessonDto createLesson(LessonDto request){
        Lesson lessonToSave = lessonMapper.toEntity(request);
        lessonRepository.save(lessonToSave);
        return lessonMapper.ToDto(lessonToSave);

    }
}
