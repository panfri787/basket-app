package com.example.matchms.service;

import com.example.matchms.domain.model.Match;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface MatchScoreEventService {
    void publishMatchResult(Match match) throws JsonProcessingException;
}
