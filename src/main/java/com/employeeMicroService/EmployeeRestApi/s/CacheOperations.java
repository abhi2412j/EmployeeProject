package com.employeeMicroService.EmployeeRestApi.s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class CacheOperations {
    @Autowired
    public EmployeeRepository employeeRepository;
    public static HashMap<Integer,Employee> cache=new HashMap<>();
    public static List<Employee> employeeList;

    @Scheduled(cron = "${cronExpression}")
    public void loadCache() {
        System.out.println("Cache loading started.......");
        employeeList=employeeRepository.findAll();

        if(!employeeList.isEmpty()){
            employeeList.forEach(employee -> cache.put(employee.getEmployeeId(),employee));
        }
        System.out.println("Cache loaded.....");
    }
}
