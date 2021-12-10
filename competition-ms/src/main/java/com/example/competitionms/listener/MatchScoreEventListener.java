package com.example.competitionms.listener;

import com.example.competitionms.config.RabbitMqConfiguration;
import com.example.competitionms.domain.event.MatchScoreEvent;
import com.example.competitionms.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MatchScoreEventListener {

    @Autowired
    TeamService teamService;

    @RabbitListener(queues = RabbitMqConfiguration.QUEUE_NAME)
    public void onMessageFromRabbitMQ(MatchScoreEvent match) {
        log.info("Match: {} received from queue", match.toString());
        teamService.updateWithMatchEvent(match);
    }


}
