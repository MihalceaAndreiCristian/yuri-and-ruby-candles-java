package ro.amihalcea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.amihalcea.model.UserModel;
import ro.amihalcea.model.UserPrincipal;
import ro.amihalcea.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()){
            System.out.println("UserModel 404.");
            throw new UsernameNotFoundException("UserModel not found.");
        }
        var user = userOpt.get();
        System.out.printf("UserModel with id %s found.\n",user.getId());

        return new UserPrincipal(user);
    }
}
