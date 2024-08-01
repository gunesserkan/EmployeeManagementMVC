package com.gunes.employee.dao;

import com.gunes.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRespository extends JpaRepository<Employee, Integer> {
    public List<Employee> findAllByOrderByLastNameAsc();
}
