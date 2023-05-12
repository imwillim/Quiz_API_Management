package com.example.quiz_api_management.user;


import com.example.quiz_api_management.common.AuthToken;
import com.example.quiz_api_management.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper){
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }


    public Optional<User> findDuplicateEmail(String email){
        return Optional.of(userRepository.findByEmail(email));
    }

    public Optional<UserDTO> checkEmailAndPassWord(String email, String password){
        String encodedPassword = userRepository.findByEmail(email).getPassword();
        if (PasswordUtil.matches(password, encodedPassword))
            return userRepository
                    .findByEmailAndPassword(email, encodedPassword)
                    .map(userDTOMapper);
        else
            return Optional.empty();
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
                .map(userDTOMapper).orElse(null);
    }

    public UserDTO updateUser(int userId, UserDTO reqBody){
        User user = userRepository.findById(userId).get();
        user.setFirstName(reqBody.getFirstName());
        user.setLastName(reqBody.getLastName());
        user.setBirthday(reqBody.getBirthday());
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
        Optional<User> newUser = userRepository.findById(userId);
        return newUser.map(userDTOMapper).orElse(null);
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
        return new AuthToken("JWT", jwtToken, TWENTY_MINUTES *60);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("No User Found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                user.getAuthorities()
        );
    }


    public AuthToken getOAuth2Token(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenType = ((OAuth2AccessToken) authentication).getTokenType().toString();
        String accessToken = ((OAuth2AccessToken) authentication).getTokenValue();
        long expiredTime = Objects.requireNonNull(((OAuth2AccessToken) authentication).getExpiresAt()).getEpochSecond();
        return new AuthToken(tokenType, accessToken, expiredTime);
    }

    public void addRole(int userId, String role) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> value.getRoles().add(role));
    }

    public boolean checkValidRole(String role){
        role = role.toLowerCase();
        String[] roles = new String[3];
        roles[0] = "STUDENT".toLowerCase();
        roles[1] = "TEACHER".toLowerCase();
        roles[2] = "COORDINATOR".toLowerCase();
        for (String existRole: roles){
            if (role.equals(existRole)) return true;
        }
        return false;
    }

    // Method for removing a role from a user
    public void removeRole(int userId, String role) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> value.getRoles().remove(role));
    }
}
