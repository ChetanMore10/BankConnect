package bankconnect_auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username is Required")
    private String username;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6, message = "password must be contain at least 6 character")
    private String password;

}