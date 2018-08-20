package com.ayantsoft.spring.security.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.spring.security.oauth2.model.security.OauthResource;
import com.ayantsoft.spring.security.oauth2.service.ResourceService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService oauthResourceService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
	public @ResponseBody List<OauthResource> getAll() {
		return oauthResourceService.getAll();
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	OauthResource get(@PathVariable String id) {
		return oauthResourceService.get(id);
	}

	@RequestMapping(value = "/resource/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	OauthResource getByResourceName(@RequestParam String resourceId) {
		return oauthResourceService.get(resourceId);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasAuthority('COMPANY_READ')")
	public ResponseEntity<?> create(@RequestBody OauthResource oauthResource) {
		oauthResourceService.create(oauthResource);
		oauthResource =oauthResourceService.get(oauthResource.getResourceId());
		return new ResponseEntity<OauthResource>(oauthResource,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/resource", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@RequestBody OauthResource oauthResource) {
		oauthResourceService.update(oauthResource);
	}

	@RequestMapping(value ="/resource/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable String id) {
		oauthResourceService.delete(id);
	}
}


© 2018 Gogs Version: 0.11.43.0330 Page: 36ms Template: 1ms  
