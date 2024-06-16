package com.neobis.neotour.model;

import com.neobis.neotour.enums.Season;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"location", "image"})
public class Trip extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season;

    @OneToOne(optional = false)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @Column(name = "max_people_amount", nullable = false)
    private Integer maxPeopleAmount;

    @Column(name = "page_visits", nullable = false)
    private Long pageVisits;

    @Column(nullable = false)
    private Boolean featured;

    public Trip(String name, String description, Season season, Integer maxPeopleAmount, Long pageVisits, Boolean featured) {
        this.name = name;
        this.description = description;
        this.season = season;
        this.maxPeopleAmount = maxPeopleAmount;
        this.pageVisits = pageVisits;
        this.featured = featured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(name, trip.name) && Objects.equals(description, trip.description) && season == trip.season && Objects.equals(maxPeopleAmount, trip.maxPeopleAmount) && Objects.equals(pageVisits, trip.pageVisits) && Objects.equals(featured, trip.featured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, season, maxPeopleAmount, pageVisits, featured);
    }

}
