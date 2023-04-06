package by.home.Course.entity.dto;

import by.home.Course.entity.enums.WorkFlowState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateRequestDto {

    Long courseId;

    LessonDto request;

    WorkFlowState currentStage;
}
