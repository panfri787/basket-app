package com.example.matchms.service.impl;

import com.example.matchms.domain.model.Match;
import com.example.matchms.repository.MatchRepository;
import com.example.matchms.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Optional<Match> putMatchResult(Long id, Match match) {
        Optional<Match> matchOp = matchRepository.findById(id);

        if (matchOp.isPresent() && !matchOp.get().isCompleted()) {
           return matchOp.map(m -> {
                m.setTeamAScore(match.getTeamAScore());
                m.setTeamBScore(match.getTeamBScore());
                m.setCompleted(true);
                matchRepository.save(m);

                return m;
            });
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Stream<Match> retrieveAllMatches() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false);
    }
}
