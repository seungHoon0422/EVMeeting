package com.ssafy.statistics.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchingDto {
    Long userid1;
    Long userid2;

    String date;
    String startTime;

    @ColumnDefault("0")
    int usedTime;

    @ColumnDefault("false")
    boolean matched;
}
