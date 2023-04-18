package com.example.quiz_api_management.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table(name = "qm_user")
@Data
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", unique = true)
    private int id;

    @Size(min = 4, max = 30)
    @Column(name = "user_name")
    private String userName;


    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "password")
    private String password;

    @Size(min = 2, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 20)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;


    @Column(name ="is_deleted")
    private boolean isDeleted;

    public User(String userName, String email, String password, String firstName, String lastName, LocalDate birthday) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.isDeleted = false;
    }

    public User() {

    }
}
