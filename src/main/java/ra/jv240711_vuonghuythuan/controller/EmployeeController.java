package ra.jv240711_vuonghuythuan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.jv240711_vuonghuythuan.exception.CustomerException;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeRequestDTO;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeResponseDTO;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeUpdateDTO;
import ra.jv240711_vuonghuythuan.model.entity.Employee;
import ra.jv240711_vuonghuythuan.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") String employeeId) throws CustomerException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @ModelAttribute EmployeeRequestDTO employeeRequestDTO) throws CustomerException {
        EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(employeeRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable("id") String employeeId,
            @Valid @ModelAttribute EmployeeUpdateDTO employeeUpdateDTO) throws CustomerException {
        if (employeeService.getEmployeeById(employeeId) == null) {
            throw new CustomerException("Không tìm thấy nhân viên với ID: " + employeeId);
        }

        EmployeeResponseDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeUpdateDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String employeeId) throws CustomerException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeResponseDTO>> searchEmployees(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<EmployeeResponseDTO> employees = employeeService.searchEmployee(keyword, page, size);
        return ResponseEntity.ok(employees);
    }
}
