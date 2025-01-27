package ro.amihalcea.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

@Converter
public class AttributeEncryptor implements AttributeConverter<String, String> {

    @Value(value="${algorithm}")
    private String algorithm;
    
    @Value(value = "${secret-key}")
    private String secretKey;
    
    @Override
    public String convertToDatabaseColumn(String attribute) {
        
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes() , algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedValue = cipher.doFinal(attribute.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting attribute", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes() , algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decodedBytes = Base64.getDecoder().decode(dbData);
            byte[] decryptedValue = cipher.doFinal(decodedBytes);
            return new String(decryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting attribute", e);
        }
    }
}


//import javax.persistence.Column;
//import javax.persistence.Convert;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class Customer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "fullname")
//    @Convert(converter = AttributeEncryptor.class)
//    private String fullname;
//
//    @Column(name = "age")
//    @Convert(converter = AttributeEncryptor.class)
//    private int age;
//
//    @Column(name = "wage")
//    @Convert(converter = AttributeEncryptor.class)
//    private double wage;
//
//    // Getters and Setters
//}

