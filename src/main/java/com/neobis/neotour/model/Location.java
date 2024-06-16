package com.neobis.neotour.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"country"})
public class Location extends BaseEntity{

    @Column(nullable = false)
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Location(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }

}
