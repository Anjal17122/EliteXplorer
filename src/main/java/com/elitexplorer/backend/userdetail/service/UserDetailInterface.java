package com.elitexplorer.backend.userdetail.service;

import com.elitexplorer.backend.userdetail.model.dto.UserDetailDto;
import com.elitexplorer.backend.userdetail.model.entity.UserDetail;

public interface UserDetailInterface {

    UserDetail saveUser(UserDetailDto dto);
}
