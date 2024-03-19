package com.aaieap.Extension.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaieap.Extension.entity.Employer;
import com.aaieap.Extension.exception.NotFoundException;
import com.aaieap.Extension.repository.EmployerRepo;
import com.aaieap.Extension.service.EmployerService;

@Service
public class EmployerImplement implements EmployerService
{
	@Autowired
	private EmployerRepo employerRepo;
	
	@Override
	public Employer saveEmployer(Employer employer)
	{
		return employerRepo.save(employer);
	}

	@Override
	public Employer getEmployerByID(Long id) throws NotFoundException
	{
		Optional<Employer> employer = employerRepo.findById(id);
		if (employer.isEmpty()) {
			throw new NotFoundException("Employer", "ID", String.valueOf(id));
		}
		System.out.println(employer.toString());
		return employer.get();
	}

	@Override
	public void deleteEmployerByID(Long id)
	{
		employerRepo.deleteById(id);
	}

	@Override
	public List<Employer> getEmployers()
	{
		return (List<Employer>) employerRepo.findAll();
	}

	@Override
	public Employer getEmployerByName(String name) throws NotFoundException
	{
		Optional<Employer> employer = employerRepo.findByName(name);
		if (employer.isEmpty()) {
			throw new NotFoundException("Employer","name", name);
		}
		System.out.println(employer.toString());
		return employer.get();
	}

}
