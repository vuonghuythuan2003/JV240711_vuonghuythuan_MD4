package ra.jv240711_vuonghuythuan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee")
@Builder
public class Employee {
    @Id
    @Column(name="emp_id", columnDefinition = "char(5)")
    private String employeeId;

    @Column(name="emp_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String employeeName;

    @Column(name="emp_birth", nullable = false)
    private LocalDate employeeBirth;

    @Column(name="sex", nullable = false)
    private boolean employeeSex;

    @Column(name="emp_address", columnDefinition = "text", nullable = false)
    private String employeeAddress;

    @Column(name="emp_email", columnDefinition = "varchar(200)", nullable = false, unique = true)
    private String employeeEmail;

    @Column(name="emp_phone", columnDefinition = "varchar(11)", nullable = false, unique = true)
    private String employeePhone;

    @Column(name="emp_avatar", columnDefinition = "text")
    private String employeeAvatar;

    @Column(name="emp_status", columnDefinition = "bit DEFAULT 1")
    private boolean employeeStatus;

    @ManyToOne
    @JoinColumn(name="dept_id", referencedColumnName = "dept_id")
    private Department department;
}
