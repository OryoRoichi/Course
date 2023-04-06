package by.home.Course.entity.dto.stateRequests;

import by.home.Course.entity.dto.HomeWorkDto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class HomeWorkStateRequestDto extends StateRequestDto {
    Long lessonId;

    HomeWorkDto request;
}

