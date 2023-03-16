package by.home.Course.entity.dto;

import by.home.Course.entity.Lesson;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    String name;
    String description;

    List<Lesson> lesson;
}
