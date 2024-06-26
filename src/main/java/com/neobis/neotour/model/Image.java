package com.neobis.neotour.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
public class Image extends BaseEntity {

    @Column(nullable = false)
    private String url;

    @Column(name = "remote_id", nullable = false)
    private String remoteId;

    public Image(String url, String remoteId) {
        this.url = url;
        this.remoteId = remoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(url, image.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
