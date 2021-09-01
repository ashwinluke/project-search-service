package com.marketlogic.app.projectrecord.repository;

import com.marketlogic.app.projectrecord.entity.ProjectRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProjectRecordRepository extends ElasticsearchRepository<ProjectRecord, Long> {

    Page<ProjectRecord> findAllByContent(String content, Pageable pageable);

    @Query("{\"wildcard\":{\"content\":\"*?0*\"}}")
    Page<ProjectRecord> findAllByContentUsingWildCard(String content, Pageable pageable);
}