package by.home.Course.entity;

import by.home.Course.entity.enums.WorkFlowState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkFlow {
    @Id
    Long id;

    WorkFlowState state;



}
