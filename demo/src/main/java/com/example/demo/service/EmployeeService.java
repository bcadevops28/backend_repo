package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return repo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return repo.findById(id).map(e -> {
            e.setName(updatedEmployee.getName());
            e.setRole(updatedEmployee.getRole());
            e.setEmail(updatedEmployee.getEmail());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        repo.deleteById(id);
    }
}
