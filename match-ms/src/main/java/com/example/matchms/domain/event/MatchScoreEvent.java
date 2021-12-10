package com.example.matchms.domain.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatchScoreEvent implements Serializable {

    private Long teamAId;
    private Integer teamAScore;
    private Long teamBId;
    private Integer teamBScore;
}
