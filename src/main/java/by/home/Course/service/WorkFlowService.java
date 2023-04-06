package by.home.Course.service;

import by.home.Course.entity.WorkFlow;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.HomeWorkReviewDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.stateRequests.LessinStateRequestDto;
import by.home.Course.entity.enums.WorkFlowState;
import by.home.Course.repository.WorkFlowRepository;
import by.home.Course.service.statemachine.StateMachine;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkFlowService {
    LessonService lessonService;
    HomeWorkService homeWorkService;
    WorkFlowRepository workFlowRepository;

    StateMachine stateMachine;

    private void saveWorkFlow(WorkFlowState state){
        WorkFlow workFlowToSave = WorkFlow.builder().state(state).build();
        workFlowRepository.save(workFlowToSave);
    }

    public LessonDto createLesson(Long courseId, LessonDto request) {
        return stateMachine.moveProcess(LessinStateRequestDto
                .builder()
                .courseId(courseId)
                .request(request)
                .build());
    }

    public HomeWorkDto createHomeWork(Long lessonId, HomeWorkDto request) {
        HomeWorkDto dto = homeWorkService.createHomeWork(lessonId, request);
        saveWorkFlow(WorkFlowState.HOMEWORK);
        return dto;
    }

    public HomeWorkDto setReview(HomeWorkDto request) {
        HomeWorkDto dtoToReturn = homeWorkService.giveReview(request);
        saveWorkFlow(WorkFlowState.HOMEWORK_REVIEW);
        return dtoToReturn;
    }
}
