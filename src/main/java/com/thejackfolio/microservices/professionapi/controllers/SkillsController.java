package com.thejackfolio.microservices.professionapi.controllers;

import com.thejackfolio.microservices.professionapi.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.professionapi.exceptions.MapperException;
import com.thejackfolio.microservices.professionapi.exceptions.ValidationException;
import com.thejackfolio.microservices.professionapi.models.Skill;
import com.thejackfolio.microservices.professionapi.services.SkillsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Skills", description = "Skill APIs")
@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService service;

    @PostMapping("/save-skills")
    public ResponseEntity<Skill> saveSkills(@RequestBody Skill skill){
        Skill response = null;
        try{
            response = service.saveSkills(skill);
        } catch (ValidationException | MapperException | DataBaseOperationException exception){
            if(skill == null){
                skill = new Skill();
                skill.setMessage(exception.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(skill);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-skills")
    public ResponseEntity<Skill> getSkills(){
        Skill response = null;
        try{
            response = service.getSkills();
        } catch (ValidationException | MapperException | DataBaseOperationException exception) {
            response = new Skill();
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
