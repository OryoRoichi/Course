package by.home.Course.service;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.Lesson;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.UncheckedHomeworkDto;
import by.home.Course.entity.mapper.HomeWorkMapper;
import by.home.Course.exceptions.CourseNotFoundException;
import by.home.Course.exceptions.HomeWorkNotFoundException;
import by.home.Course.exceptions.LessonNotFoundException;
import by.home.Course.repository.HomeWorkRepository;
import by.home.Course.repository.LessonRepository;
import by.home.Course.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomeWorkService {

    HomeWorkRepository homeWorkRepository;
    HomeWorkMapper homeWorkMapper;
    LessonRepository lessonRepository;


    public HomeWorkDto createHomeWork(Long lessonId, HomeWorkDto request) {
        HomeWork homeWorkToSave = homeWorkMapper.toEntity(request);
        Long studentId = SecurityUtil
                .getCurrentUser()
                .orElseThrow(() -> new AuthenticationServiceException("Authorization Error"))
                .getId();

        homeWorkToSave.setStudentId(studentId);

        lessonRepository.findById(lessonId)
                .map(lesson -> lesson.getHomeWork().add(homeWorkToSave))
                .orElseThrow(() -> new LessonNotFoundException(lessonId));

        homeWorkRepository.save(homeWorkToSave);
        return homeWorkMapper.ToDto(homeWorkToSave);
    }

    public UncheckedHomeworkDto viewUncheckedHomeWork(Long lessonId) {
        List<HomeWorkDto> resultList = new ArrayList<>();
        Lesson lessonToSet = lessonRepository
                .findById(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));
        List<HomeWork> homeWorkList = lessonToSet.getHomeWork();
        for (HomeWork homework : homeWorkList) {
            if (homework.getResultMark() != null) {
                resultList.add(homeWorkMapper.ToDto(homework));
            }
        }
        ;
        return UncheckedHomeworkDto.builder().homeWorkDtoList(resultList).build();
    }


    public String giveReview(HomeWorkReviewDto request) {
        HomeWork homeWork = homeWorkRepository.findById(request.getHomeWorkId())
                .orElseThrow(() -> new HomeWorkNotFoundException(request.getHomeWorkId()));
        homeWork.setResultMark(request.getResult());
        homeWork.setReview(request.getReview());
        homeWorkRepository.save(homeWork);
        return "Успех";
    }

    public HomeWorkReviewDto viewStudentsHomeWork(Long lessonId) {
        Lesson lesson = lessonRepository
                .findById(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));
        HomeWork homeWork = (HomeWork) lesson.getHomeWork();
        return HomeWorkReviewDto.builder()
                .review(homeWork.getReview())
                .content(homeWork.getContent())
                .result(homeWork.getResultMark())
                .build();


    }
}
