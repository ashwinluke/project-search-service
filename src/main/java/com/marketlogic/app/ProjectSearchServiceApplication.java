package com.marketlogic.app;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class ProjectSearchServiceApplication {

    public static final String PROJECT_RECORD = "project_record";

    public static void main(String[] args) {
        SpringApplication.run(ProjectSearchServiceApplication.class, args);
    }

    @Bean
    public boolean createIndices(RestHighLevelClient restHighLevelClient) {
        var getIndexRequest = new GetIndexRequest(PROJECT_RECORD);
        try {
            var exist = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            if (!exist) {
                log.info("Indices are not exists");
                var createIndexRequest = new CreateIndexRequest(PROJECT_RECORD);
                createIndexRequest.settings(Settings.builder()
                        .put("number_of_shards", 1)
                        .put("number_of_replicas", 0)
                        .build());
                Map<String, Map<String, String>> mappings = new HashMap<>();
                mappings.put("id", Collections.singletonMap("type", "long"));
                mappings.put("content", Collections.singletonMap("type", "text"));
                createIndexRequest.mapping(Collections.singletonMap("properties", mappings));
                var createIndexResponse = restHighLevelClient.indices()
                        .create(createIndexRequest, RequestOptions.DEFAULT);
                return createIndexResponse.isAcknowledged();
            }
            log.info("Indices are exists already");
            return true;
        } catch (IOException e) {
            log.error("Unable to create/verify the indices. Please check ELASTIC Search", e);
            System.exit(1);
        }
        return false;
    }

}
