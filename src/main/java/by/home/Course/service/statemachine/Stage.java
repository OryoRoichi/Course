package by.home.Course.service.statemachine;

import by.home.Course.entity.dto.StateRequestDto;
import by.home.Course.entity.enums.WorkFlowState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

@Data
@Builder
@AllArgsConstructor
public class Stage<T, P> {

    private WorkFlowState id;

    private Stage next;

    private Function<StateRequestDto<P>, T> process;
}
