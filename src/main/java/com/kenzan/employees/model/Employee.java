package com.kenzan.employees.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "employee" )
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id" )
    private Long id;

    @Column( name = "first_name", nullable = false)
    private String firstName;

    @Column( name = "middle_initial")
    private String middleInitial;

    @Column( name = "last_name", nullable = false)
    private String lastName;

    @Column( name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column( name = "date_of_employment", nullable = false)
    private Date dateOfEmployment;

    @Column( name = "status", nullable = false)
    private boolean status = Boolean.TRUE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
