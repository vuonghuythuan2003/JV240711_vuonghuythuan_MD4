package ra.jv240711_vuonghuythuan.model.dto;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import ra.jv240711_vuonghuythuan.validate.DepartmentUnique;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentRequestDTO {
    @NotBlank
    @DepartmentUnique(message = "Tên phòng ban không được để trống")
    private String deptName;
    private String deptDescription;
}
