package com.elitexplorer.backend.role.controller;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.role.entity.PersonRole;
import com.elitexplorer.backend.role.service.PersonRoleService;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/person/role")
public class PersonRoleController {

	@Autowired
	private PersonRoleService personRoleService;

	@GetMapping
	public ResponseEntity getAll() {
		List<PersonRole> list = personRoleService.getAll();
	//	list.remove((list.size()-1));
		return ResponseMessage.success(
				list.stream().map(DtoConvert::convert).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity updateStatus(@PathVariable("id") int id){
		return ResponseMessage.success(DtoConvert.convert(personRoleService.updateStatus(id)));
	}


}
