package ru.itmentor.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "job_position")
    private String jobPosition;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRolesAsString(Role[] roles) {
        this.roles.addAll(Arrays.asList(roles));
    }


    public User() {
    }

    public User(String name, String lastName, String email,String jobPosition,String username, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.jobPosition = jobPosition;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User { " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", email = '" + email+ '\'' +
                ", jobPosition = '" + jobPosition + '\'' +
                ", username = '" + username + '\'' +
                ", password = '" + password + '\'' +
                ", roles = '" + roles + '\'' +
                " }";
    }

    public Long getId(){return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName(){return lastName;}

    public void setLastName(String lastName) { this.lastName = lastName;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobPosition(){return jobPosition;}

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}