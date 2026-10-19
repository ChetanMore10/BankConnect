package bankconnect_account_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record AccountCreateRequest(

        @NotNull(message = "Customer ID is required")
        @Positive(message = "Customer ID must be greater than zero")
        Long customerId,

        @NotBlank(message = "Account type is required")
        @Pattern(
                regexp = "SAVINGS|CURRENT",
                message = "Account type must be SAVINGS or CURRENT"
        )
        String accountType

) {
}
