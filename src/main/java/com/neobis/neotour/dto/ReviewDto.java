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
public class ReviewDto {
    private Long id;
    private Long userId;
    private String text;
    private Long tripId;
    private Boolean deleted;
    private LocalDateTime createdAt;
}
