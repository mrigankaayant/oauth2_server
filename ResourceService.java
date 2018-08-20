package com.ayantsoft.spring.security.oauth2.service;

import java.util.List;

import com.ayantsoft.spring.security.oauth2.model.security.OauthResource;


public interface ResourceService {

	OauthResource get(String oauthResourceId);

	OauthResource getByResourceName(String resourceName);

	List<OauthResource> getAll();

	void create(OauthResource oauthResource);

	OauthResource update(OauthResource company);

	void delete(String id);

	void delete(OauthResource company);
}
