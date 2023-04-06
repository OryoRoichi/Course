package by.home.Course.entity.dto;

import by.home.Course.entity.enums.WorkFlowState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StateRequestDto<P> {

    Long courseId;

    P request;

    //WorkFlowState currentStage;
}
