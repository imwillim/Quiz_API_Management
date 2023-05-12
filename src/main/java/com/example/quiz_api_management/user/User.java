package com.example.quiz_api_management.user;

import com.example.quiz_api_management.util.PasswordUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "qm_user")
@Data

public class User implements UserDetails {
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
    @Size(min = 3, max = 100)
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

    //
    @ElementCollection
    private Set<String> roles;

    public User(String userName, String email, String password, String firstName, String lastName, LocalDate birthday) {
        this.userName = userName;
        this.email = email;
        this.password = PasswordUtil.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.isDeleted = false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("userRole.name()"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() {

    }

    public String getUserName() {
        return this.userName;
    }
}
