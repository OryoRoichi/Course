package by.home.Cource.repository;

import by.home.Cource.entity.Cource;
import by.home.Cource.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
