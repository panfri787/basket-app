package com.example.matchms.service;

import com.example.matchms.domain.model.Match;

import java.util.Optional;
import java.util.stream.Stream;

public interface MatchService {

    Optional<Match> putMatchResult(Long id, Match map);
    Stream<Match> retrieveAllMatches();
}
