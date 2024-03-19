package com.aaieap.Extension.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.aaieap.Extension.entity.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Long>, CrudRepository<Employer, Long>, PagingAndSortingRepository<Employer, Long>
{
	Optional<Employer> findByName(String name);
}
