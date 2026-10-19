package bankconnect_account_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(

        Long id,
        String accountNumber,
        Long customerId,
        BigDecimal balance,
        String accountType,
        String status,
        LocalDateTime createdAt
) {
}
