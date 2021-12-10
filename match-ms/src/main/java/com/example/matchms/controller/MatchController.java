package com.example.matchms.controller;

import com.example.matchms.domain.dto.MatchDTO;
import com.example.matchms.domain.model.Match;
import com.example.matchms.service.MatchService;
import com.example.matchms.service.MatchScoreEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class MatchController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchScoreEventService matchScoreEventService;

    @GetMapping("matches")
    public ResponseEntity<List<Match>> getAllMatches(@RequestParam(required = false) Boolean completed) {
        Stream<Match> matches = matchService.retrieveAllMatches();

        if (completed != null && completed) {
            return ResponseEntity.ok(matches.filter(Match::isCompleted)
                    .collect(Collectors.toList()));
        } else if(completed != null) {
            return ResponseEntity.ok(matches.filter(m -> !m.isCompleted())
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(matches.collect(Collectors.toList()));
        }
    }

    @PutMapping("match/{id}")
    public ResponseEntity<?> putMatchResult(@PathVariable Long id, @RequestBody MatchDTO matchDto) throws JsonProcessingException {
        Optional<Match> uptatedMatchOptional =
                matchService.putMatchResult(id, modelMapper.map(matchDto, Match.class));

        if (uptatedMatchOptional.isPresent()) {
            matchScoreEventService.publishMatchResult(uptatedMatchOptional.get());
            return ResponseEntity.ok(uptatedMatchOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match not found or previously notified");
        }
    }


}
