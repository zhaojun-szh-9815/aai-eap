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

import com.aaieap.Extension.entity.IDCSite;
import com.aaieap.Extension.exception.NotFoundException;
import com.aaieap.Extension.service.IDCSiteService;

@RestController
@CrossOrigin
@RequestMapping("/idcsite")
public class IDCSiteController
{
	@Autowired
	private IDCSiteService service;
	
	@PostMapping("/create")
	public IDCSite saveIDCSite(@RequestBody IDCSite site) {
		return service.saveIDCSite(site);
	}
	
	@GetMapping("/get/{id}")
	public IDCSite getIDCSite(@PathVariable Long id) throws NotFoundException {
		return service.getIDCSiteByID(id);
	}
	
	@DeleteMapping("/remove/{id}")
	public void deleteIDCSite(@PathVariable Long id) {
		service.deleteIDCSiteByID(id);
	}
	
	@GetMapping("/")
	public List<IDCSite> getIdcSites(){
		return service.getIdcSites();
	}
}
