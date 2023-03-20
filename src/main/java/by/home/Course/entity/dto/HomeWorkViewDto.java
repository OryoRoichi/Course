package by.home.Course.entity.dto;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.Lesson;
import by.home.Course.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class HomeWorkViewDto {

    Lesson lesson;
    User student;
    HomeWork homeWork;
}
