package com.example.competitionms.controller;

import com.example.competitionms.domain.dto.TeamDTO;
import com.example.competitionms.domain.model.Team;
import com.example.competitionms.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StandingsController {

    @Autowired
    TeamService teamService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("standings")
    public ResponseEntity<List<TeamDTO>> getCompetitionStandings() {
        List<Team> teams = teamService.retrieveTeams();

        List<TeamDTO> teamsDTOs = teams.stream()
                .map(t -> modelMapper.map(t, TeamDTO.class))
                .collect(Collectors.toList());

        for (TeamDTO dto :teamsDTOs) {
            dto.setPointsDifference(dto.getPointsScored() - dto.getPointsReceived());
        }

        List<TeamDTO> standingList = teamsDTOs.stream().sorted(Comparator.comparing(TeamDTO::getMatchesWon).reversed()
                .thenComparing(TeamDTO::getMatchesLost)
                .thenComparing(TeamDTO::getPointsDifference).reversed())
                .collect(Collectors.toList());

        return ResponseEntity.ok(standingList);
    }


}
