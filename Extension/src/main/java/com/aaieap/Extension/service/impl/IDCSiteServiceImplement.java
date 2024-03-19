package com.aaieap.Extension.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaieap.Extension.entity.IDCSite;
import com.aaieap.Extension.exception.NotFoundException;
import com.aaieap.Extension.repository.IDCSiteRepo;
import com.aaieap.Extension.service.IDCSiteService;

@Service
public class IDCSiteServiceImplement implements IDCSiteService
{
	@Autowired
	private IDCSiteRepo idcSiteRepo;

	@Override
	public IDCSite saveIDCSite(IDCSite site)
	{
		return idcSiteRepo.save(site);
	}

	@Override
	public IDCSite getIDCSiteByID(Long id) throws NotFoundException
	{
		Optional<IDCSite> site = idcSiteRepo.findById(id);
		if (site.isEmpty()) {
			throw new NotFoundException("IDCSite","ID", String.valueOf(id));
		}
		return site.get();
	}

	@Override
	public void deleteIDCSiteByID(Long id)
	{
		idcSiteRepo.deleteById(id);
	}

	@Override
	public List<IDCSite> getIdcSites()
	{
		return (List<IDCSite>) idcSiteRepo.findAll();
	}

	@Override
	public IDCSite getIDCSiteByName(String name) throws NotFoundException
	{
		Optional<IDCSite> site = idcSiteRepo.findByName(name);
		if (site.isEmpty()) {
			throw new NotFoundException("IDCSite", "name", name);
		}
		return site.get();
	}

}
