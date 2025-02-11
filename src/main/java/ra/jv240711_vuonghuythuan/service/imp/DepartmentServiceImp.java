package ra.jv240711_vuonghuythuan.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.jv240711_vuonghuythuan.exception.CustomerException;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentRequestDTO;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentResponseDTO;
import ra.jv240711_vuonghuythuan.model.dto.DepartmentUpdateDTO;
import ra.jv240711_vuonghuythuan.model.entity.Department;
import ra.jv240711_vuonghuythuan.repository.DepartmentRepository;
import ra.jv240711_vuonghuythuan.repository.EmployeeRepository;
import ra.jv240711_vuonghuythuan.service.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<DepartmentResponseDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(dept -> new DepartmentResponseDTO(
                        dept.getDeptId(),
                        dept.getDeptName(),
                        dept.getDeptDescription(),
                        dept.isStatus() ? "Hoạt động" : "Không hoạt động"
                )).collect(Collectors.toList());
    }

    @Override
    public Department findById(Integer id) throws CustomerException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new CustomerException("Không tìm thấy phòng ban với ID: " + id));
    }

    @Override
    public DepartmentResponseDTO save(DepartmentRequestDTO requestDTO) throws CustomerException {
        if (departmentRepository.existsByDeptName(requestDTO.getDeptName())) {
            throw new CustomerException("Tên phòng ban đã tồn tại");
        }
        Department department = Department.builder()
                .deptName(requestDTO.getDeptName())
                .deptDescription(requestDTO.getDeptDescription())
                .status(true)
                .build();
        department = departmentRepository.save(department);
        return new DepartmentResponseDTO(
                department.getDeptId(),
                department.getDeptName(),
                department.getDeptDescription(),
                department.isStatus() ? "Hoạt động" : "Không hoạt động"
        );
    }

    @Override
    public DepartmentResponseDTO update(Integer deptId, DepartmentUpdateDTO updateDTO) throws CustomerException {
        Department department = findById(deptId);
        if (departmentRepository.existsByDeptNameAndDeptIdNot(updateDTO.getDepartmentName(), deptId)) {
            throw new CustomerException("Tên phòng ban đã tồn tại");
        }
        department.setDeptName(updateDTO.getDepartmentName());
        department.setDeptDescription(updateDTO.getDepartmentDescription());
        department.setStatus(updateDTO.isDepartmentStatus());
        department = departmentRepository.save(department);
        return new DepartmentResponseDTO(
                department.getDeptId(),
                department.getDeptName(),
                department.getDeptDescription(),
                department.isStatus() ? "Hoạt động" : "Không hoạt động"
        );
    }

    @Override
    public void delete(Integer deptId) throws CustomerException {
        Department department = departmentRepository.findById(deptId).orElseThrow(() -> new CustomerException("Không tìm thấy id"));
        if (!employeeRepository.existsByDepartment(department)) {
            departmentRepository.delete(department);
        } else {
            throw new CustomerException("Không thể xóa được vì đang có nhân viên");
        }
    }
}

