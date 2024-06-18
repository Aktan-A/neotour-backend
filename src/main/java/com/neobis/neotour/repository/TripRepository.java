package com.neobis.neotour.repository;

import com.neobis.neotour.enums.Continent;
import com.neobis.neotour.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Page<Trip> findAllByDeletedFalse(Pageable pageable);
    @Query("SELECT t FROM Trip t JOIN t.location l JOIN l.country c WHERE t.deleted = false AND c.continent = :continent")
    Page<Trip> findAllByDeletedFalseAndContinent(Pageable pageable, Continent continent);
    Optional<Trip> findByIdAndDeletedFalse(Long id);
    Page<Trip> findAllByDeletedFalseAndFeaturedTrue(Pageable pageable);
    Page<Trip> findAllByDeletedFalseOrderByPageVisitsDesc(Pageable pageable);
    @Query("SELECT t FROM Trip t LEFT JOIN Booking b ON t.id = b.trip.id WHERE t.deleted = false " +
            "GROUP BY t.id ORDER BY COUNT(b.id) DESC")
    Page<Trip> findAllByDeletedFalseOrderByBookingsDesc(Pageable pageable);
}
