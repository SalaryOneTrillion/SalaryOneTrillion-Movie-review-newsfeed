package com.sparta.salaryonetrillionmoviereviewnewsfeed.repository;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByUsernameOrNicknameOrEmail(String username, String nickname, String email);
}
