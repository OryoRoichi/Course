package by.home.Course.service;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.mapper.HomeWorkMapper;
import by.home.Course.exceptions.CourseNotFoundException;
import by.home.Course.exceptions.LessonNotFoundException;
import by.home.Course.repository.HomeWorkRepository;
import by.home.Course.repository.LessonRepository;
import by.home.Course.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HomeWorkService {

    HomeWorkRepository homeWorkRepository;
    HomeWorkMapper homeWorkMapper;
    LessonRepository lessonRepository;


    public HomeWorkDto createHomeWork(Long lessonId,HomeWorkDto request) {
        HomeWork homeWorkToSave = homeWorkMapper.toEntity(request);
        Long studentId = SecurityUtil
                .getCurrentUser()
                .orElseThrow(()-> new AuthenticationServiceException("Authorization Error"))
                .getId();

        homeWorkToSave.setStudentId(studentId);

        lessonRepository.findById(lessonId)
                .map(lesson -> lesson.getHomeWork().add(homeWorkToSave))
                .orElseThrow(()-> new LessonNotFoundException(lessonId));

        homeWorkRepository.save(homeWorkToSave);
        return homeWorkMapper.ToDto(homeWorkToSave);
    }
}
