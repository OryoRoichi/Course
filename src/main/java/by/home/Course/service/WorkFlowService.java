package by.home.Course.service;

import by.home.Course.entity.WorkFlow;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.enums.WorkFlowState;
import by.home.Course.repository.WorkFlowRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkFlowService {
    LessonService lessonService;
    HomeWorkService homeWorkService;
    WorkFlowRepository workFlowRepository;

    private void saveWorkFlow(WorkFlowState state){
        WorkFlow workFlowToSave = WorkFlow.builder().state(state).build();
        workFlowRepository.save(workFlowToSave);
    }

    public LessonDto createLesson(Long courseId, LessonDto request) {
        LessonDto dto = lessonService.addLesson(courseId, request);
        saveWorkFlow(WorkFlowState.LESSON);
        return dto;
    }

    public HomeWorkDto createHomeWork(Long lessonId, HomeWorkDto request) {
        HomeWorkDto dto = homeWorkService.createHomeWork(lessonId, request);
        saveWorkFlow(WorkFlowState.HOMEWORK);
        return dto;
    }

    public String setReview(HomeWorkReviewDto request) {
        String stringToReturn = homeWorkService.giveReview(request);
        saveWorkFlow(WorkFlowState.HOMEWORK_REVIEW);
        return stringToReturn;
    }
}
