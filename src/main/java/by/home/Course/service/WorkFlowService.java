package by.home.Course.service;

import by.home.Course.entity.WorkFlow;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.stateRequests.StateRequestDto;
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
    StateMachine stateMachine;


    public LessonDto createLesson(LessonDto request) {
        return stateMachine.moveProcess(StateRequestDto
                .builder()
                .currentStage(WorkFlowState.LESSON)
                .request(request)
                .build());
    }

    public HomeWorkDto createHomeWork(HomeWorkDto request) {
        return stateMachine.moveProcess(StateRequestDto
                .builder()
                .currentStage(WorkFlowState.HOMEWORK)
                .request(request)
                .build());
    }
    public HomeWorkDto setReview(HomeWorkDto request) {
        return stateMachine.moveProcess(StateRequestDto
                .builder()
                .currentStage(WorkFlowState.HOMEWORK_REVIEW)
                .request(request)
                .build());
    }
}
