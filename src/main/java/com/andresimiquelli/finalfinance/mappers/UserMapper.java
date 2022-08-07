package com.andresimiquelli.finalfinance.mappers;

import com.andresimiquelli.finalfinance.dto.UserDTO;
import com.andresimiquelli.finalfinance.dto.UserPostDTO;
import com.andresimiquelli.finalfinance.dto.UserPutDTO;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User fromDTO(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getStatus()==null? null : dto.getStatus());
    }

    public static User fromDTO(UserPostDTO dto) {
        return new User(
                null,
                dto.getName(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                UserStatus.ACTIVE
        );
    }

    public static User updateData(User existing, UserPutDTO user ) {
        if(user.getName() != null)
            existing.setName(user.getName());

        if(user.getPassword() != null)
            existing.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getStatus() != null)
            existing.setStatus(user.getStatus());

        return existing;
    }
}
