package com.neobis.neotour.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"trip"})
public class Booking extends BaseEntity {

    @Column(nullable = false)
    private String phone;

    @Column
    private String comment;

    @Column(name = "people_amount", nullable = false)
    @Min(1)
    private Integer peopleAmount;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Booking(String phone, String comment, Integer peopleAmount) {
        this.phone = phone;
        this.comment = comment;
        this.peopleAmount = peopleAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(phone, booking.phone) && Objects.equals(comment, booking.comment) && Objects.equals(peopleAmount, booking.peopleAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, comment, peopleAmount);
    }
}
