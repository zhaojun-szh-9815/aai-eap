package com.aaieap.Extension.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.aaieap.Extension.entity.IDCSite;

public interface IDCSiteRepo extends JpaRepository<IDCSite, Long>, CrudRepository<IDCSite, Long>, PagingAndSortingRepository<IDCSite, Long>
{

	Optional<IDCSite> findByName(String name);
}
