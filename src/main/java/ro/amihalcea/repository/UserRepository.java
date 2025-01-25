package ro.amihalcea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.amihalcea.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    UserModel findByUsername(String username);

}
