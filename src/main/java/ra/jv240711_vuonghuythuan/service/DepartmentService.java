package ra.jv240711_vuonghuythuan.service;

import ra.jv240711_vuonghuythuan.exception.CustomerException;
import ra.jv240711_vuonghuythuan.model.entity.Department;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentRequestDTO;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentResponseDTO;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentUpdateDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponseDTO> findAll();
    Department findById(Integer id) throws CustomerException;
    DepartmentResponseDTO save(DepartmentRequestDTO requestDTO) throws CustomerException;
    DepartmentResponseDTO update(Integer deptId, DepartmentUpdateDTO updateDTO) throws CustomerException;
    void delete(Integer deptId) throws CustomerException;
}
