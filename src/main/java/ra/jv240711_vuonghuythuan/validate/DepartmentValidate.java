package ra.jv240711_vuonghuythuan.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.jv240711_vuonghuythuan.repository.DepartmentRepository;

@Component
public class DepartmentValidate implements ConstraintValidator<DepartmentUnique, String> {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !departmentRepository.existsByDeptName(value);
    }
}
