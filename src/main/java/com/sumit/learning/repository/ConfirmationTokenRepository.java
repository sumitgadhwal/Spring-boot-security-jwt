package com.sumit.learning.repository;

import com.sumit.learning.entity.ConfirmationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity,Long> {
    Optional<ConfirmationTokenEntity> findByConfirmationToken(String confirmationToken);
}
