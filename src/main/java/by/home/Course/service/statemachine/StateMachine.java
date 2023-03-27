package by.home.Course.service.statemachine;

import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.StateRequestDto;
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
    public LessonDto moveProcess(StateRequestDto stateRequest) {
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
                return currentStage.getProcess()
                        .andThen(lessonDto -> {
                            this.currentStage = currentStage.getNext();
                            return lessonDto;
                        }).apply(stateRequest);
            default:
                return LessonDto.builder().build();
        }
    }

}
