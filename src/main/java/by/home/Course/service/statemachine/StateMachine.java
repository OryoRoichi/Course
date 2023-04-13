package by.home.Course.service.statemachine;

import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.stateRequests.StateRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StateMachine {

    final Stage stage;

    Stage currentStage;

    @Transactional
    public <T, P> T moveProcess(StateRequestDto<P> stateRequest) {
        if (currentStage == null) {
            currentStage = stage;
        }
        switch (currentStage.getId()) {
            case START:
                // DO start stuff
                currentStage = currentStage.getNext();
                // return smth;
            case LESSON:
            case HOMEWORK:
            case HOMEWORK_REVIEW:
                return (T) currentStage.getProcess()
                        .andThen(lessonDto -> {
                            this.currentStage = currentStage.getNext();
                            return lessonDto;
                        }).apply(stateRequest);
            case END:
                // DO start stuff
            default:
                return (T) LessonDto.builder().build();
        }
    }
}