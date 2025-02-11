package ra.jv240711_vuonghuythuan.service;

import org.springframework.data.domain.Page;
import ra.jv240711_vuonghuythuan.exception.CustomerException;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeRequestDTO;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeResponseDTO;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeUpdateDTO;
import ra.jv240711_vuonghuythuan.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployee();
    Employee getEmployeeById(String employeeId) throws CustomerException;
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) throws CustomerException;
    EmployeeResponseDTO updateEmployee(String employeeId, EmployeeUpdateDTO employeeRequestDTO) throws CustomerException;
    void deleteEmployee(String employeeId) throws CustomerException;
    Page<EmployeeResponseDTO> searchEmployee(String keyword, int page, int size);
}
