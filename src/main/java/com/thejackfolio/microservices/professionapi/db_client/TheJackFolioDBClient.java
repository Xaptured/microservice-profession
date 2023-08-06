package com.thejackfolio.microservices.professionapi.db_client;

import com.thejackfolio.microservices.professionapi.models.Details;
import com.thejackfolio.microservices.professionapi.models.Skill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Details-db-client",url = "http://localhost:8080/clients")
public interface TheJackFolioDBClient {

    @PostMapping("/save-details")
    public ResponseEntity<Details> saveDetails(@RequestBody Details details);

    @GetMapping("/get-details")
    public ResponseEntity<Details> getDetails();

    @PostMapping("/save-skills")
    public ResponseEntity<Skill> saveSkills(@RequestBody Skill skill);

    @GetMapping("/get-skills")
    public ResponseEntity<Skill> getSkills();
}
