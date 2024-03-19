package com.aaieap.Extension.service;

import java.util.List;

import com.aaieap.Extension.entity.Employer;
import com.aaieap.Extension.exception.NotFoundException;

public interface EmployerService
{
	Employer saveEmployer(Employer employer);
	
	Employer getEmployerByID(Long id) throws NotFoundException;
	
	List<Employer> getEmployers();
	
	void deleteEmployerByID(Long id);
	
	Employer getEmployerByName(String name) throws NotFoundException;
}
