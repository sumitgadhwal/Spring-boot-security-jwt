package com.sumit.learning.repository;

import com.sumit.learning.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public Optional<UserEntity> findByEmailIgnoreCase(String email);
}
