package com.marketlogic.app.projectrecord.service;

import com.marketlogic.app.projectrecord.dto.ProjectRecordDTO;
import com.marketlogic.app.projectrecord.entity.ProjectRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectRecordService {

    ProjectRecord createProjectRecord(ProjectRecordDTO projectRecordDTO);

    Optional<ProjectRecord> getProjectRecord(Long id);

    void deleteProjectRecord(Long id);

    Iterable<ProjectRecord> insertBulk(List<ProjectRecord> projectRecords);

    Page<ProjectRecord> getProjectRecordsByContent(String content, Pageable pageable);

    Page<ProjectRecord> getProjectRecordsByContentUsingLike(String content, Pageable pageable);

    Page<ProjectRecord> getProjects(Pageable pageable);
}