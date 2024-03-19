package com.aaieap.Extension.service;

import java.util.List;

import com.aaieap.Extension.entity.QueryPageResult;
import com.aaieap.Extension.entity.RecordMaintenance;
import com.aaieap.Extension.exception.NotFoundException;

public interface RecordMaintenanceService
{
	
	RecordMaintenance saveRecordMaintenance(RecordMaintenance recordMaintenance);
	
	RecordMaintenance getRecordMaintenanceByID(Long id) throws NotFoundException;
	
//	RecordMaintenance updateRecordMaintenance(RecordMaintenance recordMaintenance) throws NotFoundException;
	
	QueryPageResult getRecordMaintenances(Integer pageIndex);
	
	void deleteRecordMaintenanceByID(Long id);
	
	List<RecordMaintenance> upload(List<RecordMaintenance> records);

}
