package com.aaieap.Extension.service;

import java.util.List;

import com.aaieap.Extension.entity.IDCSite;
import com.aaieap.Extension.exception.NotFoundException;

public interface IDCSiteService
{
	IDCSite saveIDCSite(IDCSite site);
	
	IDCSite getIDCSiteByID(Long id) throws NotFoundException;
	
	List<IDCSite> getIdcSites();
	
	void deleteIDCSiteByID(Long id);
	
	IDCSite getIDCSiteByName(String name) throws NotFoundException;
}
