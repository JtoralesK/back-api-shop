package com.apifrontGroup.shopFrontApiRest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name="users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @NotNull(message = "lastName Required")
    private String lastName;
    @NotNull(message = "email Required")
    private String email;
    @NotNull(message = "phoneNumber Required")
    private String profileImgUrl;
    @NotNull(message = "profileImgUrl Required")
    private String phoneNumber;
    @NotNull(message = "country Required")
    private String country;
    @NotNull(message = "postalCode Required")
    private String postalCode;
    @NotNull(message = "city Required")
    private String city;
    @NotNull(message = "password Required")
    private String password;
    @NotNull(message = "Gender is required")
    @ManyToOne
    @JoinColumn(name = "gender", referencedColumnName = "id")
    private Gender gender;
    @NotNull(message = "Role is required")
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date createdAt;
    @PrePersist
    protected void prePersist() {
        createdAt = new Date();
    }
    @Column(name = "active")
    private boolean active ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleType()));
    }

    @Override
    public String getPassword() {
        return password;
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
}
