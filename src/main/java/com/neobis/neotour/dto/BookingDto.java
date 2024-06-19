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
public class BookingDto {
    private Long id;
    private String phone;
    private String comment;
    private Integer peopleAmount;
    private Long tripId;
    private Boolean deleted;
    private LocalDateTime createdAt;
}
