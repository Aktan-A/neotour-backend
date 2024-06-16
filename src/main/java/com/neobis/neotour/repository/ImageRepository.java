package com.neobis.neotour.repository;

import com.neobis.neotour.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Booking, Long> {
}
