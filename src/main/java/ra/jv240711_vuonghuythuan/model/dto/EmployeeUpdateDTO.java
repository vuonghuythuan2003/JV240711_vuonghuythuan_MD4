package ra.jv240711_vuonghuythuan.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeUpdateDTO {
    @NotBlank(message = "Tên nhân viên không được để trống")
    private String employeeName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate employeeBirth;
    private boolean employeeSex;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String employeeAddress;
    @NotBlank(message = "Email không được để trống")
    @Email
    private String email;
    @Pattern(regexp = "^(?:\\+84|0)(?:3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$",message = "Sai định dạng số điện thoại")
    private String phone;
    private MultipartFile avatar;
    private Integer deptId;
}
