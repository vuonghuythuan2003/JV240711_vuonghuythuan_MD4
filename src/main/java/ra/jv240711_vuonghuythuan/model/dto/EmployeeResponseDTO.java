package ra.jv240711_vuonghuythuan.model.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponseDTO {
    private String employeeId;
    private String employeeName;
    private LocalDate employeeBirth;
    private boolean employeeSex;
    private String employeeAddress;
    private String email;
    private String phone;
    private String avatarUrl;
    private String employeeStatus;
    private String departmentName;
}
