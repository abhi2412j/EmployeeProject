package com.employeeMicroService.EmployeeRestApi.s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//Adding comment to check git is working or not

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CacheOperations cacheOperations;

    @GetMapping("/getMsg/{name}")
    public String hello(@PathVariable String name){
        String str="Good Morning ";
        return str+name;
    }

    @PostMapping("/createEmp")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeRepository.save(employee),HttpStatus.CREATED);
    }

    @PutMapping("/updateEmp")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Optional<Employee> oldemp=employeeRepository.findById(employee.getEmployeeId());
        if(oldemp.isPresent()){
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getEmp/{id}")
    public Employee getEmployeeById(@PathVariable int id){
//       return employeeRepository.findById(id);
       return CacheOperations.cache.get(id);
    }

    @GetMapping("/getAllEmp")
    public Optional<Employee> getAllEmployee(){
      //  return employeeRepository.findAll();
        cacheOperations.loadCache();
        return CacheOperations.employeeList.stream().findAny();
    }

    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmployee(@PathVariable int id){
        if(employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }
        else
            return "Employee Not Found";
    }
}
