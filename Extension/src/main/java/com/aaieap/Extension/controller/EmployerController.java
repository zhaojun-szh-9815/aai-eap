package com.aaieap.Extension.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaieap.Extension.entity.Employer;
import com.aaieap.Extension.exception.NotFoundException;
import com.aaieap.Extension.service.EmployerService;

@RestController
@CrossOrigin
@RequestMapping("/employer")
public class EmployerController
{
	@Autowired
	private EmployerService service;
	
	@PostMapping("/create")
	public Employer saveEmployer(@RequestBody Employer employer) {
		return service.saveEmployer(employer);
	}
	
	@GetMapping("/get/{id}")
	public Employer getEmployer(@PathVariable Long id) throws NotFoundException {
		return service.getEmployerByID(id);
	}
	
	@DeleteMapping("/remove/{id}")
	public void deleteEmployer(@PathVariable Long id) {
		service.deleteEmployerByID(id);
	}
	
	@GetMapping("/")
	public List<Employer> getEmployers(){
		return service.getEmployers();
	}
}
