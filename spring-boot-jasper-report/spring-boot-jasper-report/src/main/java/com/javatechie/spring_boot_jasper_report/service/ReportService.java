package com.javatechie.spring_boot_jasper_report.service;

import com.javatechie.spring_boot_jasper_report.model.Employee;
import com.javatechie.spring_boot_jasper_report.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String exportjrxml() throws JRException, FileNotFoundException {

        String path = "C:\\Users\\Acer\\Desktop\\reports\\jrxml.pdf";

        List<Employee> employees = employeeRepository.findAll();
        InputStream jrxmlStream = this.getClass().getResourceAsStream("/reports/employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return "generated successfully";
    }

    public String exportjasper() throws JRException, FileNotFoundException {

        String path = "C:\\Users\\Acer\\Desktop\\reports\\jasper.pdf";
        List<Employee> employees = employeeRepository.findAll();

        File file = ResourceUtils.getFile("classpath:reports/employees.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return "generated successfully";
    }
}
