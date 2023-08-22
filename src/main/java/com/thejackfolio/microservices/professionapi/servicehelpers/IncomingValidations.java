package com.thejackfolio.microservices.professionapi.servicehelpers;

import com.thejackfolio.microservices.professionapi.enums.SkillType;
import com.thejackfolio.microservices.professionapi.exceptions.ValidationException;
import com.thejackfolio.microservices.professionapi.models.Details;
import com.thejackfolio.microservices.professionapi.models.PersonalDetails;
import com.thejackfolio.microservices.professionapi.models.ProfessionalDetail;
import com.thejackfolio.microservices.professionapi.models.Skill;
import com.thejackfolio.microservices.professionapi.utilities.StringConstants;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingValidations {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingValidations.class);
    private static final Integer SIZE = 2;

    public void checkDetailsFromUI(Details details) throws ValidationException {
        if(details == null){
            LOGGER.error("Validation failed in IncomingValidations.class : checkDetailsFromUI for object: null");
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
        checkPersonalDetailsFromUI(details.getPersonalDetails());
        checkProfessionalDetailsFromUI(details.getProfessionalDetails());
    }

    private void checkPersonalDetailsFromUI(PersonalDetails personalDetails) throws ValidationException {
        if(personalDetails != null && personalDetails.getName() != null
                && Strings.isNotEmpty(personalDetails.getEmail()) && Strings.isNotBlank(personalDetails.getEmail())
                && personalDetails.getDateOfBirth() != null){
            LOGGER.info(StringConstants.VALIDATION_PASSED_UI);
        } else {
            LOGGER.error("Validation failed in IncomingValidations.class : checkPersonalDetailsFromUI for object: {}", personalDetails);
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
    }

    private void checkProfessionalDetailsFromUI(List<ProfessionalDetail> professionalDetails) throws ValidationException {
        if(professionalDetails != null && !professionalDetails.isEmpty()){
            for(ProfessionalDetail detail : professionalDetails){
                if(detail.getOrganization() == null || detail.getProjects()== null){
                    LOGGER.error("Validation failed in IncomingValidations.class : checkProfessionalDetailsFromUI for object: {}", professionalDetails);
                    throw new ValidationException(StringConstants.VALIDATION_ERROR);
                }
            }
            LOGGER.info(StringConstants.VALIDATION_PASSED_UI);
        } else {
            LOGGER.error("Validation failed in IncomingValidations.class : checkProfessionalDetailsFromUI for object: {}", professionalDetails);
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
    }

    public void checkDetailsFromDB(Details details) throws ValidationException {
        if(details == null){
            LOGGER.error("Validation failed in IncomingValidations.class : checkDetailsFromDB for object: null");
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
        checkPersonalDetailsFromDB(details);
        checkProfessionalDetailsFromDB(details);
    }

    private void checkPersonalDetailsFromDB(Details details) throws ValidationException {
        PersonalDetails personalDetails = details.getPersonalDetails();
        if(personalDetails != null && personalDetails.getName() != null
                && Strings.isNotEmpty(personalDetails.getEmail()) && Strings.isNotBlank(personalDetails.getEmail()) && personalDetails.getDateOfBirth() != null
                && Strings.isNotEmpty(details.getResponseMessage()) && Strings.isNotBlank(details.getResponseMessage())){
            LOGGER.info(StringConstants.VALIDATION_PASSED_DB);
        } else {
            LOGGER.error("Validation failed in IncomingValidations.class : checkPersonalDetailsFromDB for object: {}", details);
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
    }

    private void checkProfessionalDetailsFromDB(Details details) throws ValidationException {
        List<ProfessionalDetail> professionalDetails = details.getProfessionalDetails();
        if(professionalDetails != null && !professionalDetails.isEmpty()){
            for(ProfessionalDetail detail : professionalDetails){
                if(detail.getOrganization() == null || detail.getProjects() == null || details.getResponseMessage().isEmpty() || details.getResponseMessage().isBlank()){
                    LOGGER.error("Validation failed in IncomingValidations.class : checkProfessionalDetailsFromDB for object: {}", details);
                    throw new ValidationException(StringConstants.VALIDATION_ERROR);
                }
            }
            LOGGER.info(StringConstants.VALIDATION_PASSED_DB);
        } else {
            LOGGER.error("Validation failed in IncomingValidations.class : checkProfessionalDetailsFromDB for object: {}", details);
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
    }

    public void checkSkillsFromUI(Skill skill) throws ValidationException {
        if(skill == null){
            LOGGER.error("Validation failed in IncomingValidations.class : checkSkillsFromUI for object: null");
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
        checkUISkills(skill);
    }

    private void checkUISkills(Skill skill) throws ValidationException {
        if(skill.getSkills().size() == SIZE && skill.getSkills().containsKey(SkillType.PERSONAL) && skill.getSkills().containsKey(SkillType.PROFESSIONAL)
            && !skill.getSkills().get(SkillType.PERSONAL).isEmpty() && !skill.getSkills().get(SkillType.PERSONAL).isEmpty()){
            LOGGER.info(StringConstants.VALIDATION_PASSED_UI);
        } else {
            LOGGER.error("Validation failed in IncomingValidations.class : checkUISkills for object: {}", skill);
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
    }

    public void checkSkillsFromDB(Skill skill) throws ValidationException {
        if(skill == null){
            LOGGER.error("Validation failed in IncomingValidations.class : checkSkillsFromDB for object: null");
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
        checkDBSkills(skill);
    }

    private void checkDBSkills(Skill skill) throws ValidationException {
        if(skill.getSkills().size() == SIZE && skill.getSkills().containsKey(SkillType.PERSONAL) && skill.getSkills().containsKey(SkillType.PROFESSIONAL)
                && !skill.getSkills().get(SkillType.PERSONAL).isEmpty() && !skill.getSkills().get(SkillType.PERSONAL).isEmpty()
                && Strings.isNotEmpty(skill.getMessage()) && Strings.isNotBlank(skill.getMessage())){
            LOGGER.info(StringConstants.VALIDATION_PASSED_DB);
        } else {
            LOGGER.error("Validation failed in IncomingValidations.class : checkDBSkills for object: {}", skill);
            throw new ValidationException(StringConstants.VALIDATION_ERROR);
        }
    }
}
