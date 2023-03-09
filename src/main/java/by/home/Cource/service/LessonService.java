package by.home.Cource.service;

import by.home.Cource.entity.Cource;
import by.home.Cource.entity.Lesson;
import by.home.Cource.entity.dto.CourceDto;
import by.home.Cource.entity.dto.LessonDto;
import by.home.Cource.entity.mapper.LessonMapper;
import by.home.Cource.repository.LessonRepository;
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
