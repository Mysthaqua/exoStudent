package tibo.spring.exo.exostudent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tibo.spring.exo.exostudent.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
