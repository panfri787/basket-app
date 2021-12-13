package com.example.competitionms.domain.dto;

import lombok.Data;

@Data
public class TeamDTO {

    private String name;
    private Integer matchesWon;
    private Integer matchesLost;
    private Integer pointsScored;
    private Integer pointsReceived;
    private Integer pointsDifference;

    public TeamDTO calculatePointsDifference() {
        this.setPointsDifference(this.getPointsScored() - this.getPointsReceived());
        return this;
    }
}
