package com.neobis.neotour.repository;

import com.neobis.neotour.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Page<Trip> findAllByDeletedFalse(Pageable pageable);
    Optional<Trip> findByIdAndDeletedFalse(Long id);
}
