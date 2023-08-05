/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.professionapi.models;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class Details {

    private PersonalDetails personalDetails;
    private List<ProfessionalDetail> professionalDetails;
    private String responseMessage;

    public Details() {
    }

    public Details(PersonalDetails personalDetails, List<ProfessionalDetail> professionalDetails, String responseMessage) {
        this.personalDetails = personalDetails;
        this.professionalDetails = professionalDetails;
        this.responseMessage = responseMessage;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public List<ProfessionalDetail> getProfessionalDetails() {
        return professionalDetails;
    }

    public void setProfessionalDetails(List<ProfessionalDetail> professionalDetails) {
        this.professionalDetails = professionalDetails;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return Objects.equals(personalDetails, details.personalDetails) && Objects.equals(professionalDetails, details.professionalDetails) && Objects.equals(responseMessage, details.responseMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalDetails, professionalDetails, responseMessage);
    }

    @Override
    public String toString() {
        return "Details{" +
                "personalDetails=" + personalDetails +
                ", professionalDetails=" + professionalDetails +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
