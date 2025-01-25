package ro.amihalcea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.amihalcea.model.UserModel;
import ro.amihalcea.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserModel createUser(UserModel user) {

        return null;
    }
}
