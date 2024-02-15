package com.elitexplorer.backend.userdetail.service.impl;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.role.repository.PersonRoleRepository;
import com.elitexplorer.backend.security1.securityutils.UserStatus;
import com.elitexplorer.backend.userdetail.model.dto.UserDetailDto;
import com.elitexplorer.backend.userdetail.model.entity.UserDetail;
import com.elitexplorer.backend.userdetail.repository.UserDetailRepository;
import com.elitexplorer.backend.userdetail.service.UserDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserDetailImpl implements UserDetailInterface {

    @Autowired
    UserDetailRepository repository;

    @Autowired
    PersonRoleRepository personRoleRepository;

    @Override
    public UserDetail saveUser(UserDetailDto dto){
        String bcryptPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        UserDetail userDetail = DtoConvert.convert(dto);
        userDetail.setPassword(bcryptPassword);
        userDetail.setUserStatus(UserStatus.unapproved);
        userDetail.setPersonRole(personRoleRepository.findById(1).orElse(null));
        return repository.save(userDetail);

    }

}
