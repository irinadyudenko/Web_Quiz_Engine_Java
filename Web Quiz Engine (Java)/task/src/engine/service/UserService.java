package engine.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import engine.dto.UserDto;
import engine.model.User;
import engine.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean saveUser(UserDto userDto) {
        String email = userDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            return false;
        }
        String password = passwordEncoder.encode(userDto.getPassword());
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }
}

