package ra.jv240711_vuonghuythuan.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentResponseDTO {
    private int deptId;
    private String deptName;
    private String deptDescription;
    private String status;
}