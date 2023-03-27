package by.home.Course.service.statemachine;

import by.home.Course.entity.dto.LessonDto;
import by.home.Course.entity.dto.StateRequestDto;
import by.home.Course.entity.enums.WorkFlowState;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

@Data
@Builder
public class Stage {

    private WorkFlowState id;

    private Stage next;

    private Function<StateRequestDto, LessonDto> process;
}
