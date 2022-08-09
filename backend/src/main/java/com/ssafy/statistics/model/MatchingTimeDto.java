package com.ssafy.statistics.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchingTimeDto {
    String time;
    int matchingCount;
    double matchingPercent;
}
