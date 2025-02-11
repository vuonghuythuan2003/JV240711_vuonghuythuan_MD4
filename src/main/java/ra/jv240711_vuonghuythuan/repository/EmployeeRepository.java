package ra.jv240711_vuonghuythuan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.jv240711_vuonghuythuan.model.entity.Department;
import ra.jv240711_vuonghuythuan.model.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByEmployeeStatusTrue();

    boolean existsByDepartment(Department department);

    Page<Employee> findByEmployeeNameOrEmployeeAddressOrEmployeeEmailOrEmployeePhone(
            String name, String address, String email, String phone, Pageable pageable);

    boolean existsByEmployeeEmail(String email);

    boolean existsByEmployeePhone(String phone);
}
