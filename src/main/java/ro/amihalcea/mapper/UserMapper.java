package ro.amihalcea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.amihalcea.dto.UserDTO;
import ro.amihalcea.model.UserModel;
import ro.amihalcea.util.DateTimeUtil;

import java.util.Base64;

@Mapper(componentModel = "spring",imports = {DateTimeUtil.class})
public interface UserMapper {

    @Mapping(target = "fullName" , expression = "java(getFullName(user))")
    @Mapping(target = "photo", expression = "java(getBase64Photo(user.getPhoto()))")
    @Mapping(target = "birthDate", expression = "java(DateTimeUtil.getStringFromDate(user.getBirthDate()))")
    UserDTO userToDTO(UserModel user);

    @Mapping(target = "photo", expression = "java(getByteFromBase64Photo(userDTO.getPhoto()))")
    @Mapping(target = "birthDate", expression = "java(DateTimeUtil.getDateFromString(userDTO.getBirthDate()))")
    UserModel dtoToModel(UserDTO userDTO);

    default String getBase64Photo(byte[] photo){
        return (photo != null && photo.length > 0) ? Base64.getEncoder().encodeToString(photo) : null;
    }

    default byte[] getByteFromBase64Photo(String photo){
        return (photo != null && !photo.isEmpty()) ? Base64.getDecoder().decode(photo) : null;
    }

    default String getFullName(UserModel model){
        StringBuilder builder = new StringBuilder();
        String firstName = model.getFirstName();
        String lastName = model.getLastName();

        builder
                .append(removeWhiteSpacesIfNeeded(firstName))
                .append("  ")
                .append(removeWhiteSpacesIfNeeded(lastName));
        if (builder.length() == 2){
            return null;
        }
        return builder.toString();
    }

    private String removeWhiteSpacesIfNeeded(String word){
        if (word == null){
            return "";
        }
        if (!word.isBlank()){
            if (word.matches("\s")){
                word = word.replaceAll("\s+"," ");
            }
        }
        return word;
    }
}
