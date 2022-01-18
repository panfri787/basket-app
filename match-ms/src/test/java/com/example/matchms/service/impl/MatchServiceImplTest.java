package com.example.matchms.service.impl;

import com.example.matchms.domain.model.Match;
import com.example.matchms.repository.MatchRepository;
import com.example.matchms.service.MatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MatchServiceImplTest {

    @Mock
    MatchRepository matchRepositoryMock;

    @InjectMocks
    private MatchService matchService = new MatchServiceImpl();

    @Test
    public void putMatchResultTestPresentNotCompleted() {

        //given
        Match matchProvided = Match.builder()
                .id(1L)
                .completed(false)
                .teamAScore(10)
                .teamBScore(20).build();

        //when
        when(matchRepositoryMock.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(matchProvided));
        Match matchResult = matchService.putMatchResult(1L, matchProvided).get();

        //then
        verify(matchRepositoryMock).save(Mockito.any(Match.class));
        Assertions.assertEquals(matchProvided.getTeamAScore(), matchResult.getTeamAScore());
        Assertions.assertEquals(matchProvided.getTeamBScore(), matchResult.getTeamBScore());
        Assertions.assertTrue(matchResult.isCompleted());
    }

    @Test
    public void putMatchResultTestNotPresent() {

        //given

        //when
        when(matchRepositoryMock.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Optional<Match> matchResult = matchService.putMatchResult(1L, Match.builder().build());

        //then
        Assertions.assertFalse(matchResult.isPresent());
    }

    @Test
    public void putMatchResultTestPresentCompleted() {

        //given
        Match matchProvided = Match.builder()
                .completed(true).build();

        //when
        when(matchRepositoryMock.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(matchProvided));
        Optional<Match> matchResult = matchService.putMatchResult(1L, matchProvided);

        //then
        Assertions.assertFalse(matchResult.isPresent());
    }

    @Test
    public void retriveAllMatchesTest() {

        //given
        List<Match> matchList = Arrays.asList(Match.builder().id(1L).build(), Match.builder().id(2L).build());

        //when
        when(matchRepositoryMock.findAll())
                .thenReturn(matchList);
        Stream<Match> matchStreamResult = matchService.retrieveAllMatches();

        //then
        Assertions.assertEquals(matchList, matchStreamResult.collect(Collectors.toList()));
    }


}
