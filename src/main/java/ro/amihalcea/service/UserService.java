package ro.amihalcea.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.amihalcea.dto.LoginDTO;
import ro.amihalcea.dto.UserDTO;
import ro.amihalcea.enums.UserRole;
import ro.amihalcea.mapper.UserMapper;
import ro.amihalcea.model.UserModel;
import ro.amihalcea.model.UserPrincipal;
import ro.amihalcea.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final JwtService jwtService;
    private final UserMapper userMapper;


    @Autowired
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder, AuthenticationManager manager, JwtService jwtService, UserMapper userMapper) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserModel createUser(LoginDTO dto) {
        if (isUsernameValid(dto.getUsername())) {
            UserModel user = new UserModel();
            user.setUsername(dto.getUsername());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setActive(true);
            user.setRole(UserRole.GUEST);
            return repo.save(user);
        }
        throw new RuntimeException("Username is already used.");
    }


    private boolean isUsernameValid(String username) {
        var userFound = repo.findByUsername(username);
        return userFound.isEmpty();
    }

    public UserDTO getUserDetails(String username) {
        var userFound = repo.findByUsername(username).orElse(null);

        return userMapper.userToDTO(userFound);
    }
}
