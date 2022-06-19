package com.example.battleships.repositories;

import com.example.battleships.model.Ships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ships, Long> {


    Optional<Ships> findByName(String name);
}
