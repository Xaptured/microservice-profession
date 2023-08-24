package com.thejackfolio.microservices.professionapi.controllers;

import com.thejackfolio.microservices.professionapi.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.professionapi.exceptions.MapperException;
import com.thejackfolio.microservices.professionapi.exceptions.ValidationException;
import com.thejackfolio.microservices.professionapi.models.Details;
import com.thejackfolio.microservices.professionapi.services.DetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Details", description = "Detail APIs")
@RestController
@RequestMapping("/details")
public class DetailsController {

    @Autowired
    private DetailsService service;

    @PostMapping("/save-details")
    public ResponseEntity<Details> saveDetails(@RequestBody Details details){
        Details response = null;
        try{
            response = service.saveDetails(details);
        } catch (ValidationException | MapperException | DataBaseOperationException exception){
            if(details == null){
                details = new Details();
            }
            details.setResponseMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-details")
    public ResponseEntity<Details> getDetails(){
        Details response = null;
        try{
            response = service.getDetails();
        } catch (ValidationException | MapperException | DataBaseOperationException exception){
            response = new Details();
            response.setResponseMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
