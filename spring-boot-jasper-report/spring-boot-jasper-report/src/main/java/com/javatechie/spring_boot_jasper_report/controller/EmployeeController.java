package com.javatechie.spring_boot_jasper_report.controller;

import com.javatechie.spring_boot_jasper_report.model.Employee;
import com.javatechie.spring_boot_jasper_report.repository.EmployeeRepository;
import com.javatechie.spring_boot_jasper_report.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportService reportService;

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/reportjrxml")
    public String generateReportjrxml() throws JRException, FileNotFoundException {
        return reportService.exportjrxml();
    }

    @GetMapping("/reportjasper")
    public String generateReportjasper() throws JRException, FileNotFoundException {
        return reportService.exportjasper();
    }


}
