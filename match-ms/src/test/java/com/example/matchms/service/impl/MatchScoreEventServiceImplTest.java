package com.example.matchms.service.impl;

import com.example.matchms.config.RabbitMqConfiguration;
import com.example.matchms.domain.event.MatchScoreEvent;
import com.example.matchms.domain.model.Match;
import com.example.matchms.service.MatchScoreEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MatchScoreEventServiceImplTest {

    @Mock
    RabbitTemplate rabbitTemplateMock;

    @Mock
    ModelMapper modelMapperMock;

    @InjectMocks
    private MatchScoreEventService matchScoreEventService = new MatchScoreEventServiceImpl();

    @Test
    public void publishMatchResultTest() throws JsonProcessingException {

        //given
        Match m = Match.builder().build();
        MatchScoreEvent e = MatchScoreEvent.builder().build();

        //when
        when(modelMapperMock.map(eq(m), eq(MatchScoreEvent.class))).thenReturn(e);
        matchScoreEventService.publishMatchResult(m);

        //then
        Mockito.verify(rabbitTemplateMock).convertAndSend(Mockito.eq(RabbitMqConfiguration.QUEUE_NAME), eq(e));
    }




}
