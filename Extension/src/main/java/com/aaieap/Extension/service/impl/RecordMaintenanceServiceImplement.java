package com.aaieap.Extension.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aaieap.Extension.constants.Constants;
import com.aaieap.Extension.entity.Employer;
import com.aaieap.Extension.entity.IDCSite;
import com.aaieap.Extension.entity.PageInfo;
import com.aaieap.Extension.entity.QueryPageResult;
import com.aaieap.Extension.entity.RecordMaintenance;
import com.aaieap.Extension.exception.NotFoundException;
import com.aaieap.Extension.repository.RecordMaintenanceRepo;
import com.aaieap.Extension.service.EmployerService;
import com.aaieap.Extension.service.IDCSiteService;
import com.aaieap.Extension.service.RecordMaintenanceService;

@Service
public class RecordMaintenanceServiceImplement implements RecordMaintenanceService
{
	@Autowired
	private RecordMaintenanceRepo recordMaintenanceRepo;
	
	@Autowired
	private IDCSiteService idcSiteService;
	
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private Constants constants;
	
	@Override
	public RecordMaintenance saveRecordMaintenance(RecordMaintenance recordMaintenance)
	{
        IDCSite site = null;
        Employer PM = null;
        Employer onsiteTechnician = null;
		try {
            site = idcSiteService.getIDCSiteByName(recordMaintenance.getIdcSite().getName());
            recordMaintenance.setIdcSite(site);
        } catch (Exception e) {
        	e.printStackTrace();
        	site = idcSiteService.saveIDCSite(new IDCSite(recordMaintenance.getIdcSite().getName()));
        	recordMaintenance.setIdcSite(site);
		}

        try {
        	PM = employerService.getEmployerByName(recordMaintenance.getAaiPM().getName());
        	recordMaintenance.setAaiPM(PM);
        } catch (Exception e) {
        	e.printStackTrace();
        	PM = employerService.saveEmployer(new Employer(recordMaintenance.getAaiPM().getName()));
        	recordMaintenance.setAaiPM(PM);
		}

        try {
        	onsiteTechnician = employerService.getEmployerByName(recordMaintenance.getAaiOnsiteTechnician().getName());
        	recordMaintenance.setAaiOnsiteTechnician(onsiteTechnician);
        } catch (Exception e) {
        	e.printStackTrace();
        	onsiteTechnician = employerService.saveEmployer(new Employer(recordMaintenance.getAaiOnsiteTechnician().getName()));
        	recordMaintenance.setAaiOnsiteTechnician(onsiteTechnician);
		}
		return recordMaintenanceRepo.save(recordMaintenance);
	}

	@Override
	public RecordMaintenance getRecordMaintenanceByID(Long id) throws NotFoundException
	{
		Optional<RecordMaintenance> record = recordMaintenanceRepo.findById(id);
		if (record.isEmpty()) {
			throw new NotFoundException("Maintenance Record", "ID",  String.valueOf(id));
		}
		return record.get();
	}
	
	@Override
	public void deleteRecordMaintenanceByID(Long id)
	{
		recordMaintenanceRepo.deleteById(id);
	}

	@Override
	public QueryPageResult getRecordMaintenances(Integer pageIndex)
	{
		Page<RecordMaintenance> page = recordMaintenanceRepo.findAll(PageRequest.of(pageIndex, constants.QUERY_SIZE));
		PageInfo info = new PageInfo((page.getNumber()+1)*constants.QUERY_SIZE, (page.getNumber()+1)*constants.QUERY_SIZE,page.getNumber()*constants.QUERY_SIZE+1 , page.getTotalElements(), page.getTotalPages());
		return new QueryPageResult(page.getContent(), info);
	}
	
	@Override
	public List<RecordMaintenance> upload(List<RecordMaintenance> records)
	{
		Map<String, IDCSite> siteCache = new HashMap<String, IDCSite>();
		Map<String, Employer> employerCache = new HashMap<String, Employer>();
        
        for (RecordMaintenance record : records) {
            String siteName = record.getIdcSite().getName();
            String PMName = record.getAaiPMName();
            String onsiteTechinicianName = record.getAaiOnsiteTechnicianName();
            IDCSite site = null;
            Employer PM = null;
            Employer onsiteTechnician = null;
            try {
            	if (siteCache.containsKey(siteName)) {
            		site = siteCache.get(siteName);
            	} else {
                	site = idcSiteService.getIDCSiteByName(siteName);
            	}
            	record.setIdcSite(site);
            } catch (Exception e) {
            	e.printStackTrace();
            	site = idcSiteService.saveIDCSite(new IDCSite(siteName));
            	siteCache.put(siteName, site);
            	record.setIdcSite(site);
			}

            try {
            	if (employerCache.containsKey(PMName)) {
            		PM = employerCache.get(PMName);
            	} else {
            		PM = employerService.getEmployerByName(PMName);
            	}
            	record.setAaiPM(PM);
            } catch (Exception e) {
            	e.printStackTrace();
            	PM = employerService.saveEmployer(new Employer(PMName));
            	employerCache.put(PMName, PM);
            	record.setAaiPM(PM);
			}

            try {
            	if (employerCache.containsKey(onsiteTechinicianName)) {
            		onsiteTechnician = employerCache.get(onsiteTechinicianName);
            	} else {
            		onsiteTechnician = employerService.getEmployerByName(onsiteTechinicianName);
            	}
            	record.setAaiOnsiteTechnician(onsiteTechnician);
            } catch (Exception e) {
            	e.printStackTrace();
            	onsiteTechnician = employerService.saveEmployer(new Employer(onsiteTechinicianName));
            	employerCache.put(onsiteTechinicianName, onsiteTechnician);
            	record.setAaiOnsiteTechnician(onsiteTechnician);
			}
            
        }
        
        return (List<RecordMaintenance>) recordMaintenanceRepo.saveAll(records);
	}

}
