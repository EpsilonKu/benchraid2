package com.benchraid.benchraid.repositories;


import com.benchraid.benchraid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;


@Repository
@Transactional
public interface UserRepository extends JpaRepository <User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername (String email);
    @Query("SELECT u FROM User u where u.lastname = ?1 or u.firstname = ?1")
    List<User> findByLastnameOrFirstname (String name);
}
