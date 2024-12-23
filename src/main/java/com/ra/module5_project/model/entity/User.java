package com.ra.module5_project.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "username",length = 100 ,unique = true,nullable = false)
    private String username ;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "fullName",nullable = false)
    private String fullName ;

    private boolean status = false;

    @Column(name = "password",nullable = false)
    private String password;

    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id")
    , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "phone",nullable = false,unique = true)
    private String phone ;

    @Column(name = "address",nullable = false)
    private String address ;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at = new Date();

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updated_at ;

    private long activeCode ;
    private boolean statusLogin = false ;

    private boolean is_deleted = false ;

    @Builder.Default
    private double coin = 0 ;

    @Builder.Default
    private boolean getCoin = false ;
}
