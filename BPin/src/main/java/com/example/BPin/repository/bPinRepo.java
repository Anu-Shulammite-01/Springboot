package com.example.BPin.repository;

import com.example.BPin.entity.bPin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface bPinRepo extends JpaRepository<bPin,String> {
    Optional<bPin> findByUsername(String username);
}
