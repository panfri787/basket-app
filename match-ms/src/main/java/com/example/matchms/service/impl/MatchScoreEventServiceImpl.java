package com.example.matchms.service.impl;

import com.example.matchms.config.RabbitMqConfiguration;
import com.example.matchms.domain.event.MatchScoreEvent;
import com.example.matchms.domain.model.Match;
import com.example.matchms.service.MatchScoreEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MatchScoreEventServiceImpl implements MatchScoreEventService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Async
    public void publishMatchResult(Match match) throws JsonProcessingException {
        MatchScoreEvent event = modelMapper.map(match, MatchScoreEvent.class);
        rabbitTemplate.convertAndSend(RabbitMqConfiguration.QUEUE_NAME, event);
    }
}
