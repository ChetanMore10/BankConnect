package bankconnect_account_service.service;

import bankconnect_account_service.dto.AccountCreateRequest;
import bankconnect_account_service.dto.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    AccountResponse createAccount(AccountCreateRequest request);

//    List<AccountResponse> getAllAccounts();

    List<AccountResponse> getAccountsByCustomerId(Long customerId);

    AccountResponse getAccountById(Long id);

    AccountResponse getAccountByAccountNumber(String accountNumber);

    Page<AccountResponse> getAllAccounts(Pageable pageable);
}
