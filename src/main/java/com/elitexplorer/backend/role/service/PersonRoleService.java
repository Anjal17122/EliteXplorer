package com.elitexplorer.backend.role.service;

import com.elitexplorer.backend.role.entity.PersonRole;

import java.util.List;

public interface PersonRoleService {

	List<PersonRole> getAll();


	PersonRole updateStatus(int id);

}
