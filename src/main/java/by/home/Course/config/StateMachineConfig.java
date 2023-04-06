package by.home.Course.config;

import by.home.Course.entity.enums.WorkFlowState;
import by.home.Course.service.LessonService;
import by.home.Course.service.WorkFlowService;
import by.home.Course.service.statemachine.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StateMachineConfig {

    private final LessonService lessonService;

    @Bean
    public Stage stage() {
        Stage end = Stage.builder().id(WorkFlowState.END).build();
        Stage review = Stage.builder().id(WorkFlowState.HOMEWORK_REVIEW).next(end).build();
        Stage homework = Stage.builder().id(WorkFlowState.HOMEWORK).next(review).build();
        Stage lesson = Stage.builder().id(WorkFlowState.LESSON).next(homework).process(lessonService.createLesson()).build();
        Stage start = Stage.builder().id(WorkFlowState.START).next(lesson).build();
        return start;
    }
}
