package com.sprint.mission.sbblogsystem.repository;

import com.sprint.mission.sbblogsystem.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String id);
    Optional<User> findByNickname(String nickname);
    boolean existsById(String id);
    boolean existsByNickname(String nickname);
    void save(User user);
}
