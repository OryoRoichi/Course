package by.home.Course.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JoinColumn(name = "student_id")
    Long studentId;
    String content;
    String review;
    Integer resultMark;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;
}
