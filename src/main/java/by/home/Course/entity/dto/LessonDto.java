package by.home.Course.entity.dto;

import by.home.Course.entity.HomeWork;
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
