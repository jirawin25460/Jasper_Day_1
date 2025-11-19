package com.javatechie.spring_boot_jasper_report.repository;

import com.javatechie.spring_boot_jasper_report.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
