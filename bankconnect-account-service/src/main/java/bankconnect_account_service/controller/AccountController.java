package bankconnect_account_service.controller;

import bankconnect_account_service.dto.AccountCreateRequest;
import bankconnect_account_service.dto.AccountResponse;
import bankconnect_account_service.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody AccountCreateRequest request){

        AccountResponse response = accountService.createAccount(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<AccountResponse>> getAllAccount(){
//        List<AccountResponse> accountResponses = accountService.getAllAccounts();
//        return new ResponseEntity<>(accountResponses, HttpStatus.OK);
//    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponse>> getAccountByCustomerId(
            @PathVariable Long customerId){
        List<AccountResponse> responses = accountService.getAccountsByCustomerId(customerId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(
            @PathVariable Long id){
        AccountResponse response = accountService.getAccountById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountByAccountNumber(
            @PathVariable String accountNumber){
        AccountResponse response = accountService.getAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponse>> getAllAccounts(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size){
                Page<AccountResponse> accountResponses =
                        accountService.getAllAccounts(PageRequest.of(page, size));
                return new ResponseEntity<>(accountResponses, HttpStatus.OK);
    }
}