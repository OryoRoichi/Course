package by.home.Course.entity.dto.stateRequests;

import by.home.Course.entity.enums.WorkFlowState;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class StateRequestDto<Body> {
    WorkFlowState currentStage;

    Body request;

}
