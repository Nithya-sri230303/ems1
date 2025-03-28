package com.example.controller;

import com.example.model.EmployeeTable;
import com.example.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdateEmployee(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(employeeService.addOrUpdateEmployee(payload));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, payload));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchEmployee(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(employeeService.patchEmployee(id, payload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee and associated address deleted successfully!");
    }
}
