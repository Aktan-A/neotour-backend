package com.neobis.neotour.repository;

import com.neobis.neotour.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByDeletedFalse();

    Optional<Country> findByIdAndDeletedFalse(Long id);
}
