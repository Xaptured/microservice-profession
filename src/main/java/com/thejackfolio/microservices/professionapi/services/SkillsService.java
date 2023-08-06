package com.thejackfolio.microservices.professionapi.services;

import com.thejackfolio.microservices.professionapi.db_client.TheJackFolioDBClient;
import com.thejackfolio.microservices.professionapi.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.professionapi.exceptions.MapperException;
import com.thejackfolio.microservices.professionapi.exceptions.ValidationException;
import com.thejackfolio.microservices.professionapi.models.Skill;
import com.thejackfolio.microservices.professionapi.servicehelpers.IncomingValidations;
import com.thejackfolio.microservices.professionapi.utilities.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SkillsService {

    @Autowired
    private IncomingValidations validations;
    @Autowired
    private TheJackFolioDBClient client;

    public Skill saveSkills(Skill skill) throws ValidationException, DataBaseOperationException, MapperException {
        validations.checkSkillsFromUI(skill);
        ResponseEntity<Skill> response = client.saveSkills(skill);
        Skill responseBody = response.getBody();
        validations.checkSkillsFromDB(responseBody);
        if(responseBody.getMessage().equals(StringConstants.DATABASE_ERROR)){
            throw new DataBaseOperationException(responseBody.getMessage());
        } else if(responseBody.getMessage().equals(StringConstants.MAPPING_ERROR)){
            throw new MapperException(responseBody.getMessage());
        }
        return responseBody;
    }

    public Skill getSkills() throws ValidationException, DataBaseOperationException, MapperException {
        ResponseEntity<Skill> response = client.getSkills();
        Skill responseBody = response.getBody();
        validations.checkSkillsFromDB(responseBody);
        if(responseBody.getMessage().equals(StringConstants.DATABASE_ERROR)){
            throw new DataBaseOperationException(responseBody.getMessage());
        } else if(responseBody.getMessage().equals(StringConstants.MAPPING_ERROR)){
            throw new MapperException(responseBody.getMessage());
        }
        return responseBody;
    }
}
