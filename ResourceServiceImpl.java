package com.ayantsoft.spring.security.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayantsoft.spring.security.oauth2.model.Company;
import com.ayantsoft.spring.security.oauth2.model.security.OauthResource;
import com.ayantsoft.spring.security.oauth2.repository.CompanyRepository;
import com.ayantsoft.spring.security.oauth2.repository.ResourceRepository;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    @Transactional(readOnly = true)
    public OauthResource get(String oauthResourceId) {
        return resourceRepository.find(oauthResourceId);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('RESOURCE_READ') and hasAuthority('AUTHORITY_READ')")
    public OauthResource getByResourceName(String resourceName) {
        return resourceRepository.find(resourceName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OauthResource> getAll() {
        return resourceRepository.findAll();
    }

    @Override
    @Transactional
    public void create(OauthResource oauthResource) {
    	resourceRepository.create(oauthResource);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('RESOURCE_UPDATE')")
    public OauthResource update(OauthResource oauthResource) {
        return resourceRepository.update(oauthResource);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('RESOURCE_DELETE')")
    public void delete(String oauthResourceId) {
    	resourceRepository.delete(oauthResourceId);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('RESOURCE_DELETE')")
    public void delete(OauthResource oauthResource) {
    	resourceRepository.delete(oauthResource);
    }
}
