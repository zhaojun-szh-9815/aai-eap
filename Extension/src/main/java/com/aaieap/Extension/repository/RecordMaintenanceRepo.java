package com.aaieap.Extension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.aaieap.Extension.entity.RecordMaintenance;

public interface RecordMaintenanceRepo extends JpaRepository<RecordMaintenance, Long>, CrudRepository<RecordMaintenance, Long>, PagingAndSortingRepository<RecordMaintenance, Long>
{

}
