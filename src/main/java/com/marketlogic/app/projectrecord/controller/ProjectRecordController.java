package com.marketlogic.app.projectrecord.controller;

import com.marketlogic.app.projectrecord.dto.ProjectRecordDTO;
import com.marketlogic.app.projectrecord.entity.ProjectRecord;
import com.marketlogic.app.projectrecord.service.ProjectRecordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "To create a projectRecords", response = ProjectRecord.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully created the projectRecord"),
            @ApiResponse(code = 400, message = "Invalid project details"),
            @ApiResponse(code = 404, message = "ProjectRecord not found"),
            @ApiResponse(code = 500, message = "Please contact the owner")})
    public ProjectRecord createProjectRecord(@RequestBody ProjectRecordDTO projectRecordDTO) {
        return projectRecordService.createProjectRecord(projectRecordDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "To get projectRecord by Id", response = ProjectRecord.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved the projectRecord"),
            @ApiResponse(code = 400, message = "Invalid project details"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Please contact the owner")})
    public Optional<ProjectRecord> getById(@PathVariable Long id) {
        return projectRecordService.getProjectRecord(id);
    }

    @GetMapping
    @ApiOperation(value = "To get projectRecords", response = Page.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved the projectRecords"),
            @ApiResponse(code = 500, message = "Please contact the owner")})
    public Page<ProjectRecord> getAll(@RequestParam(defaultValue = "1") @ApiParam int page,
                                      @RequestParam(defaultValue = "25") @ApiParam int size) {
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        return projectRecordService.getProjects(pageable);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "To delete projectRecord by Id", response = boolean.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted the projectRecord"),
            @ApiResponse(code = 400, message = "Invalid project details"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Please contact the owner")})
    public boolean deleteById(@PathVariable Long id) {
        projectRecordService.deleteProjectRecord(id);
        return true;
    }

    @PostMapping("/_bulk")
    @ApiOperation(value = "To save projectRecords by bulk", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully created the projectRecords"),
            @ApiResponse(code = 400, message = "Invalid project details"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Please contact the owner")})
    public List<ProjectRecord> insertBulk(@RequestBody List<ProjectRecord> projectRecords) {
        return (List<ProjectRecord>) projectRecordService.insertBulk(projectRecords);
    }

    @GetMapping("/content/{content}")
    @ApiOperation(value = "To get projectRecords by content", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved the projectRecords based on content"),
            @ApiResponse(code = 400, message = "Invalid project details"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Please contact the owner")})
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
    @ApiOperation(value = "To search projectRecords by content (string contains)", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved the projectRecords based on content"),
            @ApiResponse(code = 400, message = "Invalid project details"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Please contact the owner")})
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