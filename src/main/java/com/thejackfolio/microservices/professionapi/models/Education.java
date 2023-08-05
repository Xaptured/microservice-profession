/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.professionapi.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Component
public class Education implements Serializable {

    private String collegeName;
    private String branch;
    private Date fromDate;
    private  Date toDate;

    public Education() {
    }

    public Education(String collegeName, String branch, Date fromDate, Date toDate) {
        this.collegeName = collegeName;
        this.branch = branch;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(collegeName, education.collegeName) && Objects.equals(branch, education.branch) && Objects.equals(fromDate, education.fromDate) && Objects.equals(toDate, education.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeName, branch, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "Education{" +
                "collegeName='" + collegeName + '\'' +
                ", branch='" + branch + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
