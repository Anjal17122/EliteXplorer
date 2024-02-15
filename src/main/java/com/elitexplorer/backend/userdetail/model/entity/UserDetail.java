package com.elitexplorer.backend.userdetail.model.entity;

import com.elitexplorer.backend.role.entity.PersonRole;
import com.elitexplorer.backend.security1.securityutils.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String email;

    private String phoneNo;

    private String specialization;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private PersonRole personRole;
}
