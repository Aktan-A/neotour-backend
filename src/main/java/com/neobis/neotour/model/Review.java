package com.neobis.neotour.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "reviews", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueUserAndTripReview", columnNames = {"user_id", "trip_id"})
})
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user", "trip"})
public class Review extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Review(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(text, review.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
