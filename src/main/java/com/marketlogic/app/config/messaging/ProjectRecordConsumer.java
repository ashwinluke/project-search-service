package com.marketlogic.app.config.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketlogic.app.projectrecord.dto.ProjectRecordDTO;
import com.marketlogic.app.projectrecord.service.ProjectRecordService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Profile("!mock")
public class ProjectRecordConsumer {

    @Autowired
    private ProjectRecordService projectRecordService;

    @Autowired
    private ModelMapper modelMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(
            id = "${spring.application.name}-${kafka.topics.PublishProject}",
            topics = "${kafka.topics.PublishProject}",
            clientIdPrefix = "${kafka.topics.PublishProject}-Consumer",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumePublishedProjects(
            @Payload List<String> messages,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets,
            Acknowledgment acknowledgment
    ) {
        try {
            log.debug("Beginning to consume {} Publish Project messages", messages.size());
            messages.forEach(msg -> {
                log.info("Message: {}", msg);
                try {
                    projectRecordService.createProjectRecord(objectMapper.readValue(msg, ProjectRecordDTO.class));
                    log.info("Added to the indices");
                } catch (JsonProcessingException e) {
                    log.error("Error occurred while converting it to ProjectRecordDTO! ", e);
                }
            });
            acknowledgment.acknowledge();
            log.debug("{} Publish Project data messages consumed", messages.size());
        } catch (Exception e) {
            log.error("Error occurred while streaming publish project data! ", e);
        }
    }
}
