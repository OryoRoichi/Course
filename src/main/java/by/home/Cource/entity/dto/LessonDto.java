package by.home.Cource.entity.dto;

import by.home.Cource.entity.HomeWork;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    String name;
    String description;

    List<HomeWork> homeWork;
}
