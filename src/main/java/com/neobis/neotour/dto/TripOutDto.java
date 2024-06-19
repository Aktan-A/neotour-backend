package com.neobis.neotour.dto;

import com.neobis.neotour.enums.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripOutDto {
    private Long id;
    private String name;
    private Long locationId;
    private String description;
    private Season season;
    private Long imageId;
    private ImageOutDto image;
    private Boolean featured;
    private LocalDateTime createdAt;
}
