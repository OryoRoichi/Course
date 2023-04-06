package by.home.Course.entity.dto.stateRequests;

import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.enums.WorkFlowState;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class HomeWorkStateRequestDto extends AbstractStateRequestDto {
    Long lessonId;

    HomeWorkDto request;
}

