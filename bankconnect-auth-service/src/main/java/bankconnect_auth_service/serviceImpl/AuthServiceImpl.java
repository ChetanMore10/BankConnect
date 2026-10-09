package bankconnect_auth_service.serviceImpl;

import bankconnect_auth_service.dto.RegisterRequest;
import bankconnect_auth_service.entity.User;
import bankconnect_auth_service.repository.UserRepository;
import bankconnect_auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())){
            return "Username already exist..!";
        }

        if (userRepository.existsByEmail(request.getEmail())){
            return "Email already exist..!";
        }
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("USER");

        userRepository.save(user);
        return "User Register Successfully...!";
    }
}
