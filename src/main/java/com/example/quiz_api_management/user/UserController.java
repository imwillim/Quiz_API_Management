package com.example.quiz_api_management.user;

import com.example.quiz_api_management.common.AuthToken;
import com.example.quiz_api_management.common.ResponseReturn;
import com.example.quiz_api_management.exception.DuplicateException;
import com.example.quiz_api_management.exception.NotFoundException;
import com.example.quiz_api_management.exception.NotValidCredentialException;
import com.example.quiz_api_management.util.RequestBodyError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path="/users")
    public ResponseEntity<ResponseReturn> getAllUsers(){
        List<UserDTO> users = userService.getUsers();

        return new ResponseEntity<>(new ResponseReturn(
                LocalDateTime.now(),
                "User is returned",
                HttpStatus.OK.value(),
                true,
                users), HttpStatus.OK);
    }


    @GetMapping(path = "/users/{userid}")
    public ResponseEntity<ResponseReturn> getUser(@PathVariable("userid") int userId) {
        Optional<UserDTO> user = Optional.ofNullable(userService.getUser(userId)
                .orElseThrow(() -> new NotFoundException("User not found")));

        return new ResponseEntity<>(new ResponseReturn(
                LocalDateTime.now(),
                "User is returned",
                HttpStatus.OK.value(),
                true,
                user), HttpStatus.OK);
    }

    // Create user
    @PostMapping(path="/signup")
    public ResponseEntity<ResponseReturn> signUp(@Valid @RequestBody User reqBody,
                                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return RequestBodyError.returnRequiredFields(bindingResult);
        }

        if (userService.findDuplicateEmail(reqBody.getEmail()).isPresent())
            throw new DuplicateException("Username is duplicate");

        UserDTO newUser = userService.createUser(reqBody);
        return new ResponseEntity<>(new ResponseReturn(
                LocalDateTime.now(),
                "Created new user.",
                HttpStatus.CREATED.value(),
                true,
                newUser), HttpStatus.CREATED);
    }

    @PutMapping(path = "/users/{userid}")
    public ResponseEntity<ResponseReturn> updateUser(@PathVariable("userid") int userId,
                                                     @Valid @RequestBody UserDTO reqBody){
        userService.getUser(userId).orElseThrow(() -> new NotFoundException("User not found"));

        UserDTO updatedUser = userService.updateUser(userId, reqBody);

        return new ResponseEntity<>(new ResponseReturn(
                LocalDateTime.now(),
                "Created new user.",
                HttpStatus.ACCEPTED.value(),
                true,
                updatedUser), HttpStatus.ACCEPTED);

    }

    @DeleteMapping(path = "/users/{userid}")
    public ResponseEntity<ResponseReturn> deleteUser(@PathVariable("userid") int userId){
        userService.getUser(userId).orElseThrow(() -> new NotFoundException("User not found"));

        userService.deleteUser(userId);

        return new ResponseEntity<>(new ResponseReturn(
                LocalDateTime.now(),
                "Delete user.",
                HttpStatus.NO_CONTENT.value(),
                true,
                null), HttpStatus.NO_CONTENT);

    }

    @PostMapping(path="/auth/signin")
    public ResponseEntity<ResponseReturn> login(@Valid @RequestBody User reqBody, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RequestBodyError.returnRequiredFields(bindingResult);
        }

        Optional<UserDTO> existUser = Optional.ofNullable(
                userService.checkEmailAndPassWord(reqBody.getEmail(), reqBody.getPassword())
                .orElseThrow(() -> new NotValidCredentialException("Invalid username or password.")));

        AuthToken authToken = userService.returnJWT(existUser);

        return new ResponseEntity<>(new ResponseReturn(
                LocalDateTime.now(),
                "Login successfully.",
                HttpStatus.OK.value(),
                true,
                authToken), HttpStatus.OK);
    }


}
