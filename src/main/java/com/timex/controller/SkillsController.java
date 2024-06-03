package com.timex.controller;

import com.timex.model.Skills;
import com.timex.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping("/add-skills") //http://localhost:9494/skills/add-skills
    public List<Skills> saveSkills(@RequestBody List<Skills> skills){
        return skillsService.saveSkills(skills);
    }

    @GetMapping("/search-skills")// http://localhost:9494/skills/search-skills
    @ResponseStatus(HttpStatus.OK)
    public List<Skills> searchSkills(){
        return skillsService.searchSkills();
    }

    @PutMapping("/update-search/{id}") //http://localhost:9494/skills/update-search/3
    public Skills updateskills(@PathVariable Long id, @RequestBody Skills skills){
        return skillsService.updateSkills(id, skills);
    }

    @DeleteMapping("/delete-search/{id}") //http://localhost:9494/skills/delete-search/3
    public String deleteSkills(@PathVariable Long id){
        return skillsService.deleteSkills(id);
    }
}

