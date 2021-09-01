package com.marketlogic.app.projectrecord.service.impl;

import com.marketlogic.app.projectrecord.dto.ProjectRecordDTO;
import com.marketlogic.app.projectrecord.entity.ProjectRecord;
import com.marketlogic.app.projectrecord.repository.ProjectRecordRepository;
import com.marketlogic.app.projectrecord.service.ProjectRecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectRecordServiceImpl implements ProjectRecordService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final ProjectRecordRepository projectRecordRepository;

    public ProjectRecord createProjectRecord(ProjectRecordDTO projectRecordDTO) {
        var projectRecord = modelMapper.map(projectRecordDTO, ProjectRecord.class);
        return projectRecordRepository.save(projectRecord);
    }

    public Optional<ProjectRecord> getProjectRecord(Long id) {
        return projectRecordRepository.findById(id);
    }

    public void deleteProjectRecord(Long id) {
        projectRecordRepository.deleteById(id);
    }

    public Iterable<ProjectRecord> insertBulk(List<ProjectRecord> projectRecords) {
        return projectRecordRepository.saveAll(projectRecords);
    }

    public Page<ProjectRecord> getProjectRecordsByContent(String content, Pageable pageable) {
        return projectRecordRepository.findAllByContent(content, pageable);
    }

    public Page<ProjectRecord> getProjectRecordsByContentUsingLike(String name, Pageable pageable) {
        return projectRecordRepository.findAllByContentUsingWildCard(name, pageable);
    }

    public Page<ProjectRecord> getProjects(Pageable pageable) {
        return projectRecordRepository.findAll(pageable);
    }

}