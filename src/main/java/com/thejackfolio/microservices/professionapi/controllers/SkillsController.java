package com.thejackfolio.microservices.professionapi.controllers;

import com.thejackfolio.microservices.professionapi.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.professionapi.exceptions.MapperException;
import com.thejackfolio.microservices.professionapi.exceptions.ValidationException;
import com.thejackfolio.microservices.professionapi.models.Details;
import com.thejackfolio.microservices.professionapi.models.Skill;
import com.thejackfolio.microservices.professionapi.services.SkillsService;
import com.thejackfolio.microservices.professionapi.utilities.StringConstants;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Skills", description = "Skill APIs")
@RestController
@RequestMapping("/skills")
public class SkillsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillsController.class);
    private boolean isRetryEnabled = false;

    @Autowired
    private SkillsService service;

    @Operation(
            summary = "Save skills",
            description = "Save skills and gives the same skills response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-skills")
    @Retry(name = "save-skills-db-retry", fallbackMethod = "saveSkillsDBRetry")
    public ResponseEntity<Skill> saveSkills(@RequestBody Skill skill){
        Skill response = null;
        try{
            if(isRetryEnabled){
                LOGGER.info(StringConstants.RETRY_MESSAGE);
            }
            if(!isRetryEnabled){
                isRetryEnabled = true;
            }
            response = service.saveSkills(skill);
        } catch (ValidationException | MapperException | DataBaseOperationException exception){
            if(skill == null){
                skill = new Skill();
                skill.setMessage(exception.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(skill);
            }
        }
        isRetryEnabled = false;
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Skill> saveSkillsDBRetry(Skill skill, Exception exception){
        isRetryEnabled = false;
        LOGGER.info(StringConstants.FALLBACK_MESSAGE, exception);
        Skill skillResponse = new Skill();
        skillResponse.setMessage(StringConstants.FALLBACK_MESSAGE);
        return ResponseEntity.status(HttpStatus.OK).body(skillResponse);
    }

    @Operation(
            summary = "Get skills",
            description = "Get skills with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-skills")
    @Retry(name = "get-skills-db-retry", fallbackMethod = "getSkillsDBRetry")
    public ResponseEntity<Skill> getSkills(){
        Skill response = null;
        try{
            if(isRetryEnabled){
                LOGGER.info(StringConstants.RETRY_MESSAGE);
            }
            if(!isRetryEnabled){
                isRetryEnabled = true;
            }
            response = service.getSkills();
        } catch (ValidationException | MapperException | DataBaseOperationException exception) {
            response = new Skill();
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        isRetryEnabled = false;
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Skill> getSkillsDBRetry(Exception exception){
        isRetryEnabled = false;
        LOGGER.info(StringConstants.FALLBACK_MESSAGE, exception);
        Skill skillResponse = new Skill();
        skillResponse.setMessage(StringConstants.FALLBACK_MESSAGE);
        return ResponseEntity.status(HttpStatus.OK).body(skillResponse);
    }
}
