package com.neobis.neotour.repository;

import com.neobis.neotour.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findAllByDeletedFalseAndTripId(Pageable pageable, Long tripId);
    @Query("SELECT COALESCE(SUM(b.peopleAmount), 0) FROM Booking b WHERE b.deleted = false AND b.trip.id = :tripId")
    Integer sumPeopleAmountByTripId(Long tripId);
    Optional<Booking> findByIdAndDeletedFalse(Long id);
    @Query("SELECT COUNT(b.id) = 1 FROM Booking b WHERE b.deleted = false AND b.phone = :phone and b.trip.id = :tripId")
    boolean existsByPhoneAndTripId(String phone, Long tripId);
}
