package com.example.service;

import com.example.model.EmployeeTable;
import com.example.model.AddressTable;
import com.example.dao.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<EmployeeTable> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeTable getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
    }

    public EmployeeTable addOrUpdateEmployee(Map<String, Object> payload) {
        EmployeeTable employee = new EmployeeTable();
        employee.setName((String) payload.get("name"));
        employee.setDesignation((String) payload.get("designation"));

        Map<String, Object> addressData = (Map<String, Object>) payload.get("address");
        if (addressData != null) {
            AddressTable address = new AddressTable();
            address.setStreet((String) addressData.get("street"));
            address.setCity((String) addressData.get("city"));
            address.setState((String) addressData.get("state"));

            // Set bidirectional relationship
            employee.setAddress(address);
            address.setEmployee(employee);
        }

        return employeeRepository.save(employee);
    }

    public EmployeeTable updateEmployee(Long id, Map<String, Object> payload) {
        EmployeeTable employee = getEmployeeById(id);

        if (payload.containsKey("name")) {
            employee.setName(payload.get("name").toString());
        }

        if (payload.containsKey("designation")) {
            employee.setDesignation(payload.get("designation").toString());
        }

        if (payload.containsKey("address")) {
            Map<String, Object> addressData = (Map<String, Object>) payload.get("address");
            AddressTable address = employee.getAddress() != null ? employee.getAddress() : new AddressTable();

            if (addressData.containsKey("street")) {
                address.setStreet(addressData.get("street").toString());
            }
            if (addressData.containsKey("city")) {
                address.setCity(addressData.get("city").toString());
            }
            if (addressData.containsKey("state")) { 
                address.setState(addressData.get("state").toString());
            }

            address.setEmployee(employee);
            employee.setAddress(address);
        }

        return employeeRepository.save(employee);
    }

    public EmployeeTable patchEmployee(Long id, Map<String, Object> payload) {
        EmployeeTable employee = getEmployeeById(id);

        if (payload.containsKey("name")) {
            employee.setName(payload.get("name").toString());
        }

        if (payload.containsKey("designation")) {
            employee.setDesignation(payload.get("designation").toString());
        }

        if (payload.containsKey("address")) {
            Map<String, Object> addressData = (Map<String, Object>) payload.get("address");
            AddressTable address = employee.getAddress() != null ? employee.getAddress() : new AddressTable();

            if (addressData.containsKey("street")) {
                address.setStreet(addressData.get("street").toString());
            }
            if (addressData.containsKey("city")) {
                address.setCity(addressData.get("city").toString());
            }
            if (addressData.containsKey("state")) { 
                address.setState(addressData.get("state").toString());
            }

            address.setEmployee(employee);
            employee.setAddress(address);
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        EmployeeTable employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
