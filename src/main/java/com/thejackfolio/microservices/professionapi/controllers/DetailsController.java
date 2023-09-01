package com.thejackfolio.microservices.professionapi.controllers;

import com.thejackfolio.microservices.professionapi.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.professionapi.exceptions.MapperException;
import com.thejackfolio.microservices.professionapi.exceptions.ValidationException;
import com.thejackfolio.microservices.professionapi.models.Details;
import com.thejackfolio.microservices.professionapi.services.DetailsService;
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

@Tag(name = "Details", description = "Detail APIs")
@RestController
@RequestMapping("/details")
public class DetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DetailsController.class);
    private boolean isRetryEnabled = false;

    @Autowired
    private DetailsService service;

    @Operation(
            summary = "Save details",
            description = "Save details and gives the same details response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-details")
    @Retry(name = "save-details-db-retry", fallbackMethod = "saveDetailsDBRetry")
    public ResponseEntity<Details> saveDetails(@RequestBody Details details){
        Details response = null;
        try{
            if(isRetryEnabled){
                LOGGER.info(StringConstants.RETRY_MESSAGE);
            }
            if(!isRetryEnabled){
                isRetryEnabled = true;
            }
            response = service.saveDetails(details);
        } catch (ValidationException | MapperException | DataBaseOperationException exception){
            if(details == null){
                details = new Details();
            }
            details.setResponseMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
        }
        isRetryEnabled = false;
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Details> saveDetailsDBRetry(Details details, Exception exception){
        isRetryEnabled = false;
        LOGGER.info(StringConstants.FALLBACK_MESSAGE, exception);
        Details detailsResponse = new Details();
        detailsResponse.setResponseMessage(StringConstants.FALLBACK_MESSAGE);
        return ResponseEntity.status(HttpStatus.OK).body(detailsResponse);
    }

    @Operation(
            summary = "Get details",
            description = "It gives the details as response with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-details")
    @Retry(name = "get-details-db-retry", fallbackMethod = "getDetailsDBRetry")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Details> getDetails(){
        Details response = null;
        try{
            if(isRetryEnabled){
                LOGGER.info(StringConstants.RETRY_MESSAGE);
            }
            if(!isRetryEnabled){
                isRetryEnabled = true;
            }
            response = service.getDetails();
        } catch (ValidationException | MapperException | DataBaseOperationException exception){
            response = new Details();
            response.setResponseMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        isRetryEnabled = false;
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Details> getDetailsDBRetry(Exception exception){
        isRetryEnabled = false;
        LOGGER.info(StringConstants.FALLBACK_MESSAGE, exception);
        Details detailsResponse = new Details();
        detailsResponse.setResponseMessage(StringConstants.FALLBACK_MESSAGE);
        return ResponseEntity.status(HttpStatus.OK).body(detailsResponse);
    }
}
