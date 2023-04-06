package by.home.Course.entity.dto.stateRequests;

import by.home.Course.entity.dto.LessonDto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LessinStateRequestDto extends StateRequestDto {

    Long courseId;

    LessonDto request;

}
