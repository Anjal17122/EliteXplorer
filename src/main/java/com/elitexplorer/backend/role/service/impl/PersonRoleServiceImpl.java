package com.elitexplorer.backend.role.service.impl;

import com.elitexplorer.backend.role.entity.PersonRole;
import com.elitexplorer.backend.role.repository.PersonRoleRepository;
import com.elitexplorer.backend.role.service.PersonRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonRoleServiceImpl implements PersonRoleService{

	@Autowired
	private PersonRoleRepository personRoleRepository;
	
	@Override
	public List<PersonRole> getAll() {
		return personRoleRepository.findAll();
	}

	@Override
	public PersonRole updateStatus(int id) {
		PersonRole personRole = personRoleRepository.findById(id).orElse(null);
		if (personRole.getIsActive()){
			personRole.setActive(Boolean.FALSE);
			return personRoleRepository.save(personRole);
		}
		personRole.setActive(Boolean.TRUE);
		return personRoleRepository.save(personRole);
	}



}
