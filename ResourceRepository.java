package com.ayantsoft.spring.security.oauth2.repository;

import java.util.List;
import com.ayantsoft.spring.security.oauth2.model.security.OauthResource;


public interface ResourceRepository {

	OauthResource find(String id);

	OauthResource findByResourceName(String name);

	List<OauthResource> findAll();

	void create(OauthResource oauthResource);

	OauthResource update(OauthResource oauthResource);

	void delete(String resourceId);

	void delete(OauthResource oauthResource);
}



