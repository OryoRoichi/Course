package by.home.Course.config;

import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.enums.WorkFlowState;
import by.home.Course.service.HomeWorkService;
import by.home.Course.service.LessonService;
import by.home.Course.service.statemachine.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StateMachineConfig {

    private final LessonService lessonService;
    private final HomeWorkService homeWorkService;


    @Bean
    public Stage stage() {
        Stage end = Stage.builder().id(WorkFlowState.END).build();

        Stage<HomeWorkDto, HomeWorkDto> review = Stage.<HomeWorkDto, HomeWorkDto>builder().id(WorkFlowState.HOMEWORK_REVIEW).next(end)
                .process(homeWorkService.createReview())
                .build();
        Stage<HomeWorkDto, HomeWorkDto> homework = Stage.<HomeWorkDto, HomeWorkDto>builder().id(WorkFlowState.HOMEWORK).next(review)
                .process(homeWorkService.createHomeWork())
                .build();
        Stage<LessonDto, LessonDto> lesson = Stage.<LessonDto, LessonDto>builder().id(WorkFlowState.LESSON).next(homework)
                .process(lessonService.createLesson())
                .build();
        Stage start = Stage.builder().id(WorkFlowState.START).next(lesson).build();
        return start;
    }
}
