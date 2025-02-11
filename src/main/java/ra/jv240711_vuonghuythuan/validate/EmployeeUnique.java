package ra.jv240711_vuonghuythuan.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmployeeValidate.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeUnique {
    String message() default "EmployeeUnique.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}