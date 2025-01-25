package ro.amihalcea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.amihalcea.config.AttributeEncryptor;
import ro.amihalcea.enums.UserRole;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
//    @Convert(converter = AttributeEncryptor.class)
    private String password;
    private String email;
    private String firstName;
    private String lastName;
//    @Convert(converter = AttributeEncryptor.class)
    private Date birthDate;
    private UserRole role;
    private byte[] photo;
    private boolean isActive;


}
