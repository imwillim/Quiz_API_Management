package com.example.quiz_api_management.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int userId);

    // Optional<User> findByEmail(String email);

    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

}
