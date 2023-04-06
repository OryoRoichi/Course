package by.home.Course.entity.dto;

import by.home.Course.entity.enums.WorkFlowState;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class HomeWorkDto {
    Long id;
    String content;
    String review;
    Integer result;
    Long lessonId;

}
