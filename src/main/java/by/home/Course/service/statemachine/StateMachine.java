package by.home.Course.service.statemachine;

import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.stateRequests.AbstractStateRequestDto;
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
    public <I extends AbstractStateRequestDto, O> O moveProcess(I stateRequest) {
        if (currentStage == null) {
            currentStage = stage;
        }
        if (stateRequest.getCurrentStage() != currentStage.getId()) {
            throw new RuntimeException("Этап изменился. Обновите страницу");
        }
        switch (currentStage.getId()) {
            case START:
                // DO start stuff
                currentStage = currentStage.getNext();
            case LESSON:
                return (O) currentStage.getProcess()
                        .andThen(lessonDto -> {
                            this.currentStage = currentStage.getNext();
                            return lessonDto;
                        }).apply(stateRequest);
            case HOMEWORK:
            case HOMEWORK_REVIEW:
                return (O) currentStage.getProcess()
                        .andThen(homeWorkDto -> {
                            this.currentStage = currentStage.getNext();
                            return homeWorkDto;
                        }).apply(stateRequest);

            case END:
                // DO start stuff
            default:
                return null;
        }
    }

}
