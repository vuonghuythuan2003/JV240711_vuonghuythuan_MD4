package ra.jv240711_vuonghuythuan.validate;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ra.jv240711_vuonghuythuan.repository.EmployeeRepository;

public class EmployeeValidate implements ConstraintValidator<EmployeeUnique, String> {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}