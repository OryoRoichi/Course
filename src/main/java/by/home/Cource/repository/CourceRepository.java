package by.home.Cource.repository;

import by.home.Cource.entity.Cource;
import by.home.Cource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourceRepository extends JpaRepository<Cource,Long> {

}
