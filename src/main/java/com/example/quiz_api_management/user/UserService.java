package com.example.quiz_api_management.user;


import com.example.quiz_api_management.common.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper){
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public Optional<User> findDuplicateEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<UserDTO> checkEmailAndPassWord(String email, String password){
        return userRepository
                .findByEmailAndPassword(email, password)
                .map(userDTOMapper);
    }

    public List<UserDTO> getUsers(){
        return userRepository.findAll().stream().map(userDTOMapper).toList();
    }

    public Optional<UserDTO> getUser(int userId){
        return userRepository.findById(userId).map(userDTOMapper);
    }

    public UserDTO createUser(User reqBody){
        User newUser = new User(reqBody.getUserName(), reqBody.getEmail(), reqBody.getPassword(),
                reqBody.getFirstName(), reqBody.getLastName(), reqBody.getBirthday());
        userRepository.save(newUser);
        return userRepository.findByEmailAndPassword(reqBody.getEmail(), reqBody.getPassword())
                .stream().findAny()
                .map(userDTOMapper).get();
    }

    public UserDTO updateUser(int userId, UserDTO reqBody){
        User user = userRepository.findById(userId).get();
        user.setFirstName(reqBody.getFirstName());
        user.setLastName(reqBody.getLastName());
        user.setBirthday(reqBody.getBirthday());
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
        Optional<User> newUser = userRepository.findById(userId);
        return newUser.isPresent() ? newUser.map(userDTOMapper).get() : null;
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public AuthToken returnJWT(Optional<UserDTO> existUser) {
        int TWENTY_MINUTES = 20;
        String jwtToken = Jwts.builder()
                .setSubject(existUser.get().getUserName())
                .claim("User", existUser)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(TWENTY_MINUTES).toInstant()))
                .compact();
        return new AuthToken(jwtToken, TWENTY_MINUTES *60);
    }

    public boolean checkJwt(AuthToken authToken){
        Jws<Claims> jws = Jwts.parserBuilder().build().parseClaimsJws(authToken.getToken());
        return !Objects.equals(jws, null);
    }

    public void signOut(AuthToken authToken){
        int EXPIRED_SECOND = 0;
        authToken.setExpirationTime(EXPIRED_SECOND);
    }

    public void changePassword(Optional<UserDTO> existUser, UserPassword userPassword){
        User user = userRepository.findById(existUser.get().getId()).get();
        user.setPassword(userPassword.getPassword());
        userRepository.save(user);
    }
}
