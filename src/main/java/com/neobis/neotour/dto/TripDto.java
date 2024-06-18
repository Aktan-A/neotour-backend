package com.neobis.neotour.dto;

import com.neobis.neotour.enums.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private String name;
    private Long locationId;
    private String description;
    private Season season;
    private Long imageId;
    private Integer maxPeopleAmount;
    private Long pageVisits;
    private Boolean featured;
    private boolean deleted;
    private LocalDateTime createdAt;
}
