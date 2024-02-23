package com.elitexplorer.backend.userdetail.model.entity;

import com.elitexplorer.backend.role.entity.PersonRole;
import com.elitexplorer.backend.security1.securityutils.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    private String filename;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private Date registerDate;

    private Date modifiedDate;



    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private PersonRole personRole;

    @PrePersist
    public void initialModifiedDate(){
        this.modifiedDate = new Date();
    }
}

