package com.ssafy.statistics.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchingRateDto {
    int totalCount;
    int matchingCount;
    double matchingPercent;
}
