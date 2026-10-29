
package bankconnect_account_service.serviceImpl;

import bankconnect_account_service.dto.AccountCreateRequest;
import bankconnect_account_service.dto.AccountResponse;
import bankconnect_account_service.entity.Account;
import bankconnect_account_service.exception.ResourceNotFoundException;
import bankconnect_account_service.repository.AccountRepository;
import bankconnect_account_service.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountResponse createAccount(AccountCreateRequest request) {

        Account account = new Account();

        String accountNumber = "ACC"
                + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();

        account.setAccountNumber(accountNumber);
        account.setCustomerId(request.customerId());
        account.setBalance(BigDecimal.ZERO);
        account.setAccountType(
                request.accountType().trim().toUpperCase());
        account.setStatus("ACTIVE");
        account.setCreatedAt(LocalDateTime.now());

        Account savedAccount = accountRepository.save(account);

        return mapToResponse(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> getAccountsByCustomerId(
            Long customerId) {

        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account not found with ID: " + id));
        return mapToResponse(account);
    }

    @Override
    public AccountResponse getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found with account number: "
                                        + accountNumber));
        return mapToResponse(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountResponse> getAllAccounts(Pageable pageable) {

        return accountRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> searchAccounts(String accountType, String status) {

        if (accountType != null) {
            accountType = accountType.trim().toUpperCase();
        }
        if (status != null) {
            status = status.trim().toUpperCase();
        }

        List<Account> accounts;

        if (accountType != null && status != null) {
            accounts = accountRepository.findByAccountTypeAndStatus(accountType, status);
        } else if (accountType != null) {
            accounts = accountRepository.findByAccountType(accountType);
        } else if (status != null) {
            accounts = accountRepository.findByStatus(status);
        } else {
            accounts = accountRepository.findAll();
        }

        return accounts.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AccountResponse mapToResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getCustomerId(),
                account.getBalance(),
                account.getAccountType(),
                account.getStatus(),
                account.getCreatedAt()
        );
    }
}