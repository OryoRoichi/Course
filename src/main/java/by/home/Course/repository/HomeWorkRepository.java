package by.home.Course.repository;

import by.home.Course.entity.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWork,Long>  {

    @Query("select h from HomeWork h where h.lesson = :lessonId")
    List<HomeWork> findByLesson(Long lessonId);

}
