package com.elitexplorer.backend.userdetail.service;

import com.elitexplorer.backend.security1.securityutils.UserStatus;
import com.elitexplorer.backend.userdetail.model.dto.UserDetailDto;
import com.elitexplorer.backend.userdetail.model.entity.UserDetail;

import java.util.List;

public interface UserDetailInterface {

    UserDetail saveUser(UserDetailDto dto);

    UserDetail changeStatus(int id, UserStatus status);

    List<UserDetail> getAll();
}
