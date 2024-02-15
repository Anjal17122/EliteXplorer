package com.elitexplorer.backend.userdetail.model.dto;

import com.elitexplorer.backend.role.entity.PersonRole;
import com.elitexplorer.backend.security1.securityutils.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class UserDetailDto {

    private int id;

    private String fullName;

    private String email;

    private String phoneNo;

    private String specialization;

    private String username;

    private String password;

    private UserStatus userStatus;


    private int personRoleId;

    private String personRole;

    private String filename;
}
