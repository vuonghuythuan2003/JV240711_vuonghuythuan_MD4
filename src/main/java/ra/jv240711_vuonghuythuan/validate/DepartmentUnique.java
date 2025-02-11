package ra.jv240711_vuonghuythuan.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DepartmentValidate.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmentUnique {
    String message() default "Tên phòng ban không được truyền vào";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}