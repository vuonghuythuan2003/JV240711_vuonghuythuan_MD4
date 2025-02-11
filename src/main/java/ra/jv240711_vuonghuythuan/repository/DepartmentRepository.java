package ra.jv240711_vuonghuythuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.jv240711_vuonghuythuan.model.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByDeptName(String deptName);
    boolean existsByDeptNameAndDeptIdNot(String deptName, Integer deptId);
}
