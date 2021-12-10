package com.example.competitionms.service.impl;

import com.example.competitionms.domain.event.MatchScoreEvent;
import com.example.competitionms.domain.model.Team;
import com.example.competitionms.repository.TeamRepository;
import com.example.competitionms.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void updateWithMatchEvent(MatchScoreEvent match) {

        Team teamA = teamRepository.findById(match.getTeamAId()).get();
        Team teamB = teamRepository.findById(match.getTeamBId()).get();

        if(match.getTeamAScore() > match.getTeamBScore()) {
            teamA.setMatchesWon(teamA.getMatchesWon() + 1);
            teamB.setMatchesLost(teamB.getMatchesLost() + 1);
        } else {
            teamB.setMatchesWon(teamB.getMatchesWon() + 1);
            teamA.setMatchesLost(teamA.getMatchesLost() + 1);
        }

        teamA.setPointsScored(teamA.getPointsScored() + match.getTeamAScore());
        teamA.setPointsReceived(teamA.getPointsReceived() + match.getTeamBScore());

        teamB.setPointsScored(teamB.getPointsScored() + match.getTeamBScore());
        teamB.setPointsReceived(teamB.getPointsReceived() + match.getTeamAScore());

        teamRepository.save(teamA);
        teamRepository.save(teamB);
    }

    @Override
    public List<Team> retrieveTeams() {
        return teamRepository.findAllBy();
    }
}
