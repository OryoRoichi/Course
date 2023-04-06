package by.home.Course.entity.dto.stateRequests;

import by.home.Course.entity.enums.WorkFlowState;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Builder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public abstract class AbstractStateRequestDto {
    WorkFlowState currentStage;
}
