package com.gunes.employee.service;


import com.gunes.employee.dao.EmployeeRespository;
import com.gunes.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRespository employeeRespository;

    public EmployeeServiceImpl(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRespository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRespository.findById(theId);
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not found employee id - " + theId);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRespository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRespository.deleteById(theId);
    }
}
