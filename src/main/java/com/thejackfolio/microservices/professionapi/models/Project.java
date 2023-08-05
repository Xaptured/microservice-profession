/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.professionapi.models;


import com.thejackfolio.microservices.professionapi.enums.TechStack;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Component
public class Project implements Serializable {

    private String name;
    private String description;
    private List<TechStack> techStacks;
    private List<String> roles;

    public Project() {
    }

    public Project(String name, String description, List<TechStack> techStacks, List<String> roles) {
        this.name = name;
        this.description = description;
        this.techStacks = techStacks;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TechStack> getTechStacks() {
        return techStacks;
    }

    public void setTechStacks(List<TechStack> techStacks) {
        this.techStacks = techStacks;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) && Objects.equals(description, project.description) && Objects.equals(techStacks, project.techStacks) && Objects.equals(roles, project.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, techStacks, roles);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", techStacks=" + techStacks +
                ", roles=" + roles +
                '}';
    }
}
