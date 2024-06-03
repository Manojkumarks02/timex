package com.timex.service;

import com.timex.model.Skills;

import java.util.List;

public interface SkillsService {
    List<Skills> saveSkills(List<Skills> skills);

    List<Skills> searchSkills();

    Skills updateSkills(Long id, Skills skills);

    String deleteSkills(Long id);
}
