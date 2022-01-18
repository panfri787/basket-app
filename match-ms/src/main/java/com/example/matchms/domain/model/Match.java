package com.example.matchms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MATCHES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
