package com.example.competitionms.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Team {

    @Id
    private Long id;
    private String name;
    private Integer matchesWon;
    private Integer matchesLost;
    private Integer pointsScored;
    private Integer pointsReceived;

}
