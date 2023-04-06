package by.home.Course.service.statemachine;

import by.home.Course.entity.dto.stateRequests.StateRequestDto;
import by.home.Course.entity.enums.WorkFlowState;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

@Data
@Builder
public class Stage <Body, Output>    {

    private WorkFlowState id;

    private Stage next;

    private Function<StateRequestDto<Body>, Output> process;
}
