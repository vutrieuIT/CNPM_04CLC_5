package com.project.schoolmanagement.service;

import com.project.schoolmanagement.dto.GradeDTO;

import java.util.List;

public interface IGradeService {
    List<GradeDTO> getScoreTable(Long class_id, Long subject_id);
    void save(List<GradeDTO> list, Long subject_id);
}
