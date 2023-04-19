package com.example.quiz_api_management.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user){
        return new UserDTO(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.isDeleted()
        );
    }
}
