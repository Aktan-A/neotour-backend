package com.neobis.neotour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {
    private Long id;
    private String city;
    private Long countryId;
    private Boolean deleted;
    private LocalDateTime createdAt;
}
