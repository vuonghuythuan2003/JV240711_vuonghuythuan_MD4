package ra.jv240711_vuonghuythuan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.jv240711_vuonghuythuan.exception.CustomerException;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentRequestDTO;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentResponseDTO;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentUpdateDTO;
import ra.jv240711_vuonghuythuan.model.entity.Department;
import ra.jv240711_vuonghuythuan.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAllDepartments(){
        List<DepartmentResponseDTO> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id) throws CustomerException {
        Department deparments = departmentService.findById(id);
        return new ResponseEntity<>(deparments, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartments(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO) throws CustomerException {
        DepartmentResponseDTO departmentResponseDTO = departmentService.save(departmentRequestDTO);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@Valid @RequestBody DepartmentUpdateDTO departmentUpdateDTO, @PathVariable int id) throws CustomerException {
        if(departmentService.findById(id) == null) {
            throw new CustomerException("Không tìm thấy id cần cập nhật" +id);
        }
        DepartmentResponseDTO updateDepart = departmentService.update(id, departmentUpdateDTO);
        return new ResponseEntity<>(updateDepart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id) throws CustomerException {
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
