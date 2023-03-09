package by.home.Cource.entity.dto;

import by.home.Cource.entity.Lesson;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourceDto {
    String name;
    String description;

    List<Lesson> lesson;
}
