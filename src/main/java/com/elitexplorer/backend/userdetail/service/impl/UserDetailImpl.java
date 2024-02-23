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

import java.util.Date;
import java.util.List;

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
        userDetail.setRegisterDate(new Date());
        return repository.save(userDetail);
    }

    @Override
    public UserDetail changeStatus(int id, UserStatus status){
        UserDetail userDetail = repository.findById(id).orElse(null);
        userDetail.setUserStatus(status);
        return repository.save(userDetail);
    }

    @Override
    public List<UserDetail> getAll(){
        return repository.findAll();
    }

}
