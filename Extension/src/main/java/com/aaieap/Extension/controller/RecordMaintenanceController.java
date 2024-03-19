package com.aaieap.Extension.controller;

import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aaieap.Extension.entity.QueryPageResult;
import com.aaieap.Extension.entity.RecordMaintenance;
import com.aaieap.Extension.exception.NotFoundException;
import com.aaieap.Extension.service.RecordMaintenanceService;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

@RestController
@CrossOrigin
@RequestMapping("/record/maintenance")
public class RecordMaintenanceController
{
	@Autowired
	private RecordMaintenanceService service;
	
	@PostMapping("/save")
	public RecordMaintenance saveRecordMaintenance(@RequestBody RecordMaintenance recordMaintenance) {
		return service.saveRecordMaintenance(recordMaintenance);
	}
	
	@GetMapping("/get/{id}")
	public RecordMaintenance getRecordMaintenance(@PathVariable Long id) throws NotFoundException {
		return service.getRecordMaintenanceByID(id);
	}
	
	@DeleteMapping("/remove/{id}")
	public void deleteRecordMaintenance(@PathVariable Long id) {
		service.deleteRecordMaintenanceByID(id);
	}
	
	@GetMapping("/list/{page}")
	public QueryPageResult getRecordMaintenances(@PathVariable Integer page) {
		return service.getRecordMaintenances(page);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadRecords(@RequestParam("file") MultipartFile file) {
		try {
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<RecordMaintenance> records = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    RecordMaintenance.class,
                    params
            );

            records = service.upload(records);

            return new ResponseEntity<String>("Effect rows: " + records.size(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Could not upload the file: " + file.getOriginalFilename() + "!", HttpStatus.BAD_REQUEST);
        }
	}
}
