package com.neobis.neotour.model;

import com.neobis.neotour.enums.Continent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "countries", indexes = @Index(columnList = "continent"))
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Country extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Continent continent;

    public Country(String name, Continent continent) {
        this.name = name;
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) && continent == country.continent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, continent);
    }
}
