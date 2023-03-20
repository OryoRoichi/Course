package by.home.Course.repository;

import by.home.Course.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByLogin(final String login);

//    @Query("select u from user u where org.user_id in(select org.user_id from org_user_course org where org.course_id =:courseId)")
//    List<User> findUserByCourseId(final Long courseId);
}
