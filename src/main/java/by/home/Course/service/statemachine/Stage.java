package by.home.Course.service.statemachine;

import by.home.Course.entity.dto.stateRequests.AbstractStateRequestDto;
import by.home.Course.entity.enums.WorkFlowState;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

@Data
@Builder
public class Stage <I extends AbstractStateRequestDto,O>    {

    private WorkFlowState id;

    private Stage next;

    private Function<I, O> process;
}
