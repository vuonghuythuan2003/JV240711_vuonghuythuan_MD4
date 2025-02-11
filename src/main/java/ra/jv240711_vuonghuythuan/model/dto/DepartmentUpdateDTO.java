package ra.jv240711_vuonghuythuan.model.dto;

import lombok.*;
import ra.jv240711_vuonghuythuan.validate.DepartmentUnique;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentUpdateDTO {
    @DepartmentUnique(message = "Tên phòng ban đã tồn tại")
    private String departmentName;
    private String departmentDescription;
    private boolean departmentStatus;
}
