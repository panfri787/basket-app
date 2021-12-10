package com.example.competitionms.service;

import com.example.competitionms.domain.event.MatchScoreEvent;
import com.example.competitionms.domain.model.Team;

import java.util.List;

public interface TeamService {
    void updateWithMatchEvent(MatchScoreEvent match);
    List<Team> retrieveTeams();
}
