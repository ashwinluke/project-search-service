package com.marketlogic.app.projectrecord.controller;

import com.marketlogic.app.projectrecord.dto.ProjectRecordDTO;
import com.marketlogic.app.projectrecord.entity.ProjectRecord;
import com.marketlogic.app.projectrecord.service.ProjectRecordService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project-record")
@RequiredArgsConstructor
public class ProjectRecordController {

    @Autowired
    private final ProjectRecordService projectRecordService;

    @PostMapping
    public ProjectRecord createProjectRecord(@RequestBody ProjectRecordDTO projectRecordDTO) {
        return projectRecordService.createProjectRecord(projectRecordDTO);
    }

    @GetMapping("/{id}")
    public Optional<ProjectRecord> getById(@PathVariable Long id) {
        return projectRecordService.getProjectRecord(id);
    }

    @GetMapping
    public Page<ProjectRecord> getAll(@RequestParam(defaultValue = "1") @ApiParam int page,
                                      @RequestParam(defaultValue = "25") @ApiParam int size) {
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        return projectRecordService.getProjects(pageable);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        projectRecordService.deleteProjectRecord(id);
        return true;
    }

    @PostMapping("/_bulk")
    public List<ProjectRecord> insertBulk(@RequestBody List<ProjectRecord> projectRecords) {
        return (List<ProjectRecord>) projectRecordService.insertBulk(projectRecords);
    }

    @GetMapping("/content/{content}")
    public Page<ProjectRecord> findAllByContent(@PathVariable String content,
                                                @RequestParam(defaultValue = "1") @ApiParam int page,
                                                @RequestParam(defaultValue = "25") @ApiParam int size) {
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        return projectRecordService.getProjectRecordsByContent(content, pageable);
    }

    @GetMapping("/content/search/{content}")
    public Page<ProjectRecord> findAllByContentUsingLike(@PathVariable String content,
                                                         @RequestParam(defaultValue = "1") @ApiParam int page,
                                                         @RequestParam(defaultValue = "25") @ApiParam int size) {
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        return projectRecordService.getProjectRecordsByContentUsingLike(content, pageable);
    }
}