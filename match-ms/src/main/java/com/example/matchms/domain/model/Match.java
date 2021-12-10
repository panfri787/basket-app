package com.example.matchms.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MATCHES")
@Data
public class Match {

    @Id
    private Long id;
    private Long teamAId;
    private Integer teamAScore;
    private Long teamBId;
    private Integer teamBScore;
    private boolean completed;
}
