package bankconnect_auth_service.service;

import bankconnect_auth_service.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String register(RegisterRequest request);
}
