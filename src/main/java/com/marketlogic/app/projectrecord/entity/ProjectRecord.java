package com.marketlogic.app.projectrecord.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "project_record", shards = 1, replicas = 0, refreshInterval = "5s", createIndex = false)
public class ProjectRecord {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String content;

}