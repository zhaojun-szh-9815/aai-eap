package com.aaieap.Extension;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import com.aaieap.Extension.entity.Employer;
import com.aaieap.Extension.entity.IDCSite;
import com.aaieap.Extension.entity.RecordMaintenance;
import com.aaieap.Extension.service.EmployerService;
import com.aaieap.Extension.service.IDCSiteService;
import com.aaieap.Extension.service.RecordMaintenanceService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExtensionApplicationTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private IDCSiteService idcSiteService;
	
	@Autowired
	private RecordMaintenanceService recordMaintenanceService;

	@Test
	void contextLoads() {
	}

	@Test
	public void addRecords() {
		Employer employer1 = new Employer();
		employer1.setName("Yishan Xu");
		Employer employer2 = new Employer();
		employer2.setName("Fei Ai");
		
		employer1 = employerService.saveEmployer(employer1);
		employer2 = employerService.saveEmployer(employer2);
		
		IDCSite site = new IDCSite();
		site.setName("DC12");
		
		site = idcSiteService.saveIDCSite(site);
		
		RecordMaintenance recordMaintenance = new RecordMaintenance();
		recordMaintenance.setAaiPM(employer1);
		recordMaintenance.setAaiOnsiteTechnician(employer2);
		recordMaintenance.setIdcSite(site);
		recordMaintenance.setClient("DIDI");
		recordMaintenance.setDefectiveSerialNumber("CPU2 & CPU1D0");
		recordMaintenance.setDeviceSpecification("H3C R4900 G5");
		recordMaintenance.setEndDate(Date.valueOf("2024-2-5"));
		recordMaintenance.setStartDate(Date.valueOf("2024-1-25"));
		recordMaintenance.setTaskAssignedDate(Date.valueOf("2024-1-29"));
		recordMaintenance.setGdFolderLink("https://drive.google.com/drive/folders/1RToeShETkNJvXWrgBaw50cv1gIlt7lPe");
		recordMaintenance.setGdFolderName("网络技术团队");
		recordMaintenance.setOnsiteTechnicianDispatched(4);
		recordMaintenance.setOnsiteWorkingHours(8);
		recordMaintenance.setPartNumber("R4900 G5");
		recordMaintenance.setQuantity(1);
		recordMaintenance.setTaskDescription("Server Hardware replacement");
		recordMaintenance.setTicketInfo("serverFaultReport_136981 DIMM&CPU error");
		recordMaintenance.setTaskName("H3C Server Maintenance Service");
		
		recordMaintenanceService.saveRecordMaintenance(recordMaintenance);
	}
}
