package bankconnect_account_service.exception;

public class MethodArgumentNotValidException extends RuntimeException{

    public MethodArgumentNotValidException(String message){
        super(message);
    }
}
