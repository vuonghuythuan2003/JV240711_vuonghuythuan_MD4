package ra.jv240711_vuonghuythuan.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="department")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name", columnDefinition="varchar(100)", nullable= false, unique= true)
    private String deptName;

    @Column(name="dept_description", columnDefinition = "text")
    private String deptDescription;

    @Column(name = "status", columnDefinition = "bit DEFAULT 1")
    private boolean status;
}
