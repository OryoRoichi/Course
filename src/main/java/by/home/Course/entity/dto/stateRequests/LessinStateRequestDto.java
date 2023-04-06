package by.home.Course.entity.dto.stateRequests;

import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.enums.WorkFlowState;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LessinStateRequestDto extends AbstractStateRequestDto {

    Long courseId;

    LessonDto request;

}
