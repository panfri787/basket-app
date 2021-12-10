package com.example.matchms.repository;

import com.example.matchms.domain.model.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
}
