package ra.jv240711_vuonghuythuan.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.jv240711_vuonghuythuan.exception.CustomerException;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeRequestDTO;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeResponseDTO;
import ra.jv240711_vuonghuythuan.model.dto.EmployeeUpdateDTO;
import ra.jv240711_vuonghuythuan.model.entity.Department;
import ra.jv240711_vuonghuythuan.model.entity.Employee;
import ra.jv240711_vuonghuythuan.repository.DepartmentRepository;
import ra.jv240711_vuonghuythuan.repository.EmployeeRepository;
import ra.jv240711_vuonghuythuan.service.EmployeeService;
import ra.jv240711_vuonghuythuan.service.UploadFileService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<EmployeeResponseDTO> getAllEmployee() {
        return employeeRepository.findAllByEmployeeStatusTrue()
                .stream()
                .sorted(Comparator.comparing(Employee::getEmployeeName))
                .map(emp -> EmployeeResponseDTO.builder()
                        .employeeId(emp.getEmployeeId())
                        .employeeName(emp.getEmployeeName())
                        .employeeBirth(emp.getEmployeeBirth())
                        .employeeSex(emp.isEmployeeSex())
                        .employeeAddress(emp.getEmployeeAddress())
                        .email(emp.getEmployeeEmail())
                        .phone(emp.getEmployeePhone())
                        .avatarUrl(emp.getEmployeeAvatar())
                        .employeeStatus(emp.isEmployeeStatus() ? "1 - Đang làm việc" : "0 - Nghỉ việc")
                        .departmentName(emp.getDepartment().getDeptName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(String employeeId) throws CustomerException {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomerException("Không tìm thấy nhân viên"));
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) throws CustomerException {
        if (employeeRequestDTO.getDeptId() == null) {
            throw new CustomerException("Phòng ban không được để trống");
        }

        if (employeeRepository.existsByEmployeeEmail(employeeRequestDTO.getEmployeeEmail())) {
            throw new CustomerException("Email đã tồn tại");
        }

        if (employeeRepository.existsByEmployeePhone(employeeRequestDTO.getPhone())) {
            throw new CustomerException("Số điện thoại đã tồn tại");
        }

        String fileName = uploadFileService.uploadFile(employeeRequestDTO.getAvatar());

        Department department = departmentRepository.findById(employeeRequestDTO.getDeptId())
                .orElseThrow(() -> new CustomerException("Không tìm thấy phòng ban"));

        Employee employee = Employee.builder()
                .employeeId(employeeRequestDTO.getEmployeeId())
                .employeeName(employeeRequestDTO.getEmployeeName())
                .employeeBirth(employeeRequestDTO.getEmployeeBirth())
                .employeeSex(employeeRequestDTO.isEmployeeSex())
                .employeeAddress(employeeRequestDTO.getEmployeeAddress())
                .employeeEmail(employeeRequestDTO.getEmployeeEmail())
                .employeePhone(employeeRequestDTO.getPhone())
                .employeeAvatar(fileName)
                .employeeStatus(true)
                .department(department)
                .build();

        employeeRepository.save(employee);

        return new EmployeeResponseDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeBirth(),
                employee.isEmployeeSex(),
                employee.getEmployeeAddress(),
                employee.getEmployeeEmail(),
                employee.getEmployeePhone(),
                fileName,
                "1 - Đang làm việc",
                department.getDeptName()
        );
    }

    @Override
    public EmployeeResponseDTO updateEmployee(String employeeId, EmployeeUpdateDTO employeeRequestDTO) throws CustomerException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomerException("Không tìm thấy ID của nhân viên"));

        if (employeeRepository.existsByEmployeeEmail(employeeRequestDTO.getEmail())) {
            throw new CustomerException("Email này đã tồn tại, không thể cập nhật");
        }

        if (employeeRepository.existsByEmployeePhone(employeeRequestDTO.getPhone())) {
            throw new CustomerException("Số điện thoại này đã tồn tại, không thể cập nhật");
        }

        String avatarUrl = employee.getEmployeeAvatar();
        if (employeeRequestDTO.getAvatar() != null && !employeeRequestDTO.getAvatar().isEmpty()) {
            avatarUrl = uploadFileService.uploadFile(employeeRequestDTO.getAvatar());
        }

        employee.setEmployeeName(employeeRequestDTO.getEmployeeName());
        employee.setEmployeeBirth(employeeRequestDTO.getEmployeeBirth());
        employee.setEmployeeSex(employeeRequestDTO.isEmployeeSex());
        employee.setEmployeeAddress(employeeRequestDTO.getEmployeeAddress());
        employee.setEmployeeEmail(employeeRequestDTO.getEmail());
        employee.setEmployeePhone(employeeRequestDTO.getPhone());
        employee.setEmployeeAvatar(avatarUrl);

        employeeRepository.save(employee);

        return new EmployeeResponseDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeBirth(),
                employee.isEmployeeSex(),
                employee.getEmployeeAddress(),
                employee.getEmployeeEmail(),
                employee.getEmployeePhone(),
                avatarUrl,
                employee.isEmployeeStatus() ? "1 - Đang làm việc" : "0 - Nghỉ việc",
                employee.getDepartment().getDeptName()
        );
    }

    @Override
    public void deleteEmployee(String employeeId) throws CustomerException {
        Employee employee = getEmployeeById(employeeId);
        employee.setEmployeeStatus(false);
        employeeRepository.save(employee);
    }

    @Override
    public Page<EmployeeResponseDTO> searchEmployee(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findByEmployeeNameOrEmployeeAddressOrEmployeeEmailOrEmployeePhone(
                keyword, keyword, keyword, keyword, pageable);

        return employeePage.map(emp -> new EmployeeResponseDTO(
                emp.getEmployeeId(),
                emp.getEmployeeName(),
                emp.getEmployeeBirth(),
                emp.isEmployeeSex(),
                emp.getEmployeeAddress(),
                emp.getEmployeeEmail(),
                emp.getEmployeePhone(),
                emp.getEmployeeAvatar(),
                emp.isEmployeeStatus() ? "1 - Đang làm việc" : "0 - Nghỉ việc",
                emp.getDepartment().getDeptName()
        ));
    }
}
