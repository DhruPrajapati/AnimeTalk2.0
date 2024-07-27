package com.AnimeTalk.Repository;

import com.AnimeTalk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmail(String email);

    @Query("Select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% Or u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
}
