package com.timex.service.impl;

import com.timex.model.Skills;
import com.timex.repository.SkillsRepository;
import com.timex.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    private SkillsRepository skillsRepository;

    @Override
    public List<Skills> saveSkills(List<Skills> skills) {
        return skillsRepository.saveAll(skills);
    }

    @Override
    public List<Skills> searchSkills() {
        return skillsRepository.findAll();
    }

    @Override
    public Skills updateSkills(Long id, Skills skills) {
        Skills updateSkills = skillsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Skills not found for this id..!"));
        updateSkills.setVersion(skills.getVersion());
        updateSkills.setExperience(skills.getExperience());
        return skillsRepository.save(updateSkills);
    }

    @Override
    public String deleteSkills(Long id) {
        Optional<Skills> deleteSkills = skillsRepository.findById(id);
        if(deleteSkills.isPresent()){
            skillsRepository.deleteById(id);
            return "Skills with ID " + id + " has been successfully deleted.";
        }
        else {
            throw new IllegalArgumentException("skills with ID " + id + " not found.");
        }
    }
}
