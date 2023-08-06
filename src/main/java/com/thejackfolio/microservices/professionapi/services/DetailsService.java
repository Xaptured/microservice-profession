package com.thejackfolio.microservices.professionapi.services;

import com.thejackfolio.microservices.professionapi.db_client.TheJackFolioDBClient;
import com.thejackfolio.microservices.professionapi.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.professionapi.exceptions.MapperException;
import com.thejackfolio.microservices.professionapi.exceptions.ValidationException;
import com.thejackfolio.microservices.professionapi.models.Details;
import com.thejackfolio.microservices.professionapi.servicehelpers.IncomingValidations;
import com.thejackfolio.microservices.professionapi.utilities.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DetailsService {

    @Autowired
    private IncomingValidations validations;
    @Autowired
    private TheJackFolioDBClient client;

    public Details saveDetails(Details details) throws ValidationException, DataBaseOperationException, MapperException {
        validations.checkDetailsFromUI(details);
        ResponseEntity<Details> response = client.saveDetails(details);
        Details responseBody = response.getBody();
        validations.checkDetailsFromDB(responseBody);
        if(responseBody.getResponseMessage().equals(StringConstants.DATABASE_ERROR)){
            throw new DataBaseOperationException(responseBody.getResponseMessage());
        }
        else if(responseBody.getResponseMessage().equals(StringConstants.MAPPING_ERROR)){
            throw new MapperException(responseBody.getResponseMessage());
        }
        return responseBody;
    }

    public Details getDetails() throws ValidationException, DataBaseOperationException, MapperException {
        ResponseEntity<Details> response = client.getDetails();
        Details responseBody = response.getBody();
        validations.checkDetailsFromDB(responseBody);
        if(responseBody.getResponseMessage().equals(StringConstants.DATABASE_ERROR)){
            throw new DataBaseOperationException(responseBody.getResponseMessage());
        }
        else if(responseBody.getResponseMessage().equals(StringConstants.MAPPING_ERROR)){
            throw new MapperException(responseBody.getResponseMessage());
        }
        return responseBody;
    }
}
