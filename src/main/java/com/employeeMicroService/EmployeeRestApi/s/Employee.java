package com.employeeMicroService.EmployeeRestApi.s;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Employee {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int employeeId;
    private String employeeName;
    private  String employeeAddress;
    private long salary;
}
