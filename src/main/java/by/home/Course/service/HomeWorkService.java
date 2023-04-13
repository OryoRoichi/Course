package by.home.Course.service;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.Lesson;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.UncheckedHomeworkDto;
import by.home.Course.entity.dto.stateRequests.StateRequestDto;
import by.home.Course.entity.mapper.HomeWorkMapper;
import by.home.Course.exceptions.HomeWorkNotFoundException;
import by.home.Course.exceptions.LessonNotFoundException;
import by.home.Course.repository.HomeWorkRepository;
import by.home.Course.repository.LessonRepository;
import by.home.Course.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomeWorkService {

    HomeWorkRepository homeWorkRepository;
    HomeWorkMapper homeWorkMapper;
    LessonRepository lessonRepository;

    public Function<StateRequestDto<HomeWorkDto>, HomeWorkDto> createHomeWork() {
        return stageRequest -> createHomeWork(stageRequest.getRequest());
    }
    public Function<StateRequestDto<HomeWorkDto>, HomeWorkDto> createReview(){
        return  stageRequest -> giveReview(stageRequest.getRequest());
    }

    public HomeWorkDto createHomeWork(HomeWorkDto request) {
        HomeWork homeWorkToSave = homeWorkMapper.toEntity(request);
        Long studentId = SecurityUtil
                .getCurrentUser()
                .orElseThrow(() -> new AuthenticationServiceException("Authorization Error"))
                .getId();
        homeWorkToSave.setStudentId(studentId);

        Lesson lessonToSet = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new LessonNotFoundException(request.getLessonId()));
        homeWorkToSave.setLesson(lessonToSet);
        homeWorkRepository.save(homeWorkToSave);
        return homeWorkMapper.ToDto(homeWorkToSave);
    }


    public UncheckedHomeworkDto viewUncheckedHomeWork(Long lessonId) {
        List<HomeWork> homeWorkList = homeWorkRepository.findByLesson(lessonId);
        List<HomeWorkDto> resultList = homeWorkList.stream().filter(homeWork-> ObjectUtils.isNotEmpty(homeWork))
                .map(homeWork -> homeWorkMapper.ToDto(homeWork)).collect(Collectors.toList());
        return UncheckedHomeworkDto.builder().homeWorkDtoList(resultList).build();
    }

    public HomeWorkDto giveReview(HomeWorkDto request) {
        HomeWork homeWork = homeWorkRepository.findById(request.getId())
                .orElseThrow(() -> new HomeWorkNotFoundException(request.getId()));
        homeWork.setResultMark(request.getResult());
        homeWork.setReview(request.getReview());
        homeWorkRepository.save(homeWork);
        return HomeWorkDto.builder()
                .review(homeWork.getReview())
                .content(homeWork.getContent())
                .result(homeWork.getResultMark())
                .build();
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
