package by.home.Cource.repository;

import by.home.Cource.entity.Cource;
import by.home.Cource.entity.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWork,Long>  {

}
