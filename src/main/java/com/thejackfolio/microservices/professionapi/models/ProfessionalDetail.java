/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.professionapi.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Component
public class ProfessionalDetail implements Serializable {

    private Organization organization;
    private List<Project> projects;

    public ProfessionalDetail() {
    }

    public ProfessionalDetail(Organization organization, List<Project> projects) {
        this.organization = organization;
        this.projects = projects;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessionalDetail that = (ProfessionalDetail) o;
        return Objects.equals(organization, that.organization) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization, projects);
    }

    @Override
    public String toString() {
        return "ProfessionalDetails{" +
                "organization=" + organization +
                ", projects=" + projects +
                '}';
    }
}
