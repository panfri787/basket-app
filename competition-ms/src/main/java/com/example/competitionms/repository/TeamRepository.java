package com.example.competitionms.repository;

import com.example.competitionms.domain.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team,Long> {

    List<Team> findAllBy();

}
