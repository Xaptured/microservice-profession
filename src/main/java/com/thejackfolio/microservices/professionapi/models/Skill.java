/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.professionapi.models;

import com.thejackfolio.microservices.professionapi.enums.SkillType;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class Skill implements Serializable {

    private Map<SkillType, List<String>> skills;
    private String message;

    public Skill() {
    }

    public Skill(Map<SkillType, List<String>> skills, String message) {
        this.skills = skills;
        this.message = message;
    }

    public Map<SkillType, List<String>> getSkills() {
        return skills;
    }

    public void setSkills(Map<SkillType, List<String>> skills) {
        this.skills = skills;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(skills, skill.skills) && Objects.equals(message, skill.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills, message);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skills=" + skills +
                ", message='" + message + '\'' +
                '}';
    }
}
