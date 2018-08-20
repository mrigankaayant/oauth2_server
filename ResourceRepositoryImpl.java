package com.ayantsoft.spring.security.oauth2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.ayantsoft.spring.security.oauth2.model.Company;
import com.ayantsoft.spring.security.oauth2.model.security.Authority;
import com.ayantsoft.spring.security.oauth2.model.security.AuthorityGroup;
import com.ayantsoft.spring.security.oauth2.model.security.AuthorityGroup_;
import com.ayantsoft.spring.security.oauth2.model.security.OauthResource;
import com.ayantsoft.spring.security.oauth2.model.security.OauthResource_;
@Repository
public class ResourceRepositoryImpl implements ResourceRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public OauthResource find(String resourceId) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OauthResource> query = builder.createQuery(OauthResource.class);

		Root<OauthResource> root = query.from(OauthResource.class);
		Fetch<OauthResource, AuthorityGroup> authorityGroupFetch = root.fetch(OauthResource_.authorityGroups, JoinType.LEFT);
		Fetch<AuthorityGroup, Authority> authorityFetch = authorityGroupFetch.fetch(AuthorityGroup_.authorities, JoinType.LEFT);
		query.select(root).distinct(true);
		Predicate idPredicate = builder.equal(root.get(OauthResource_.resourceId), resourceId);
		query.where(builder.and(idPredicate));

		return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
	}

	@Override
	public OauthResource findByResourceName(String resourceName) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OauthResource> query = builder.createQuery(OauthResource.class);

		Root<OauthResource> root = query.from(OauthResource.class);
		Fetch<OauthResource, AuthorityGroup> authorityGroupFetch = root.fetch(OauthResource_.authorityGroups, JoinType.LEFT);
		Fetch<AuthorityGroup, Authority> authorityFetch = authorityGroupFetch.fetch(AuthorityGroup_.authorities, JoinType.LEFT);
		query.select(root).distinct(true);
		Predicate idPredicate = builder.equal(root.get(OauthResource_.resourceName), resourceName);
		query.where(builder.and(idPredicate));

		return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
	}

	@Override
	public List<OauthResource> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OauthResource> query = builder.createQuery(OauthResource.class);
        Root<OauthResource> root = query.from(OauthResource.class);
		Fetch<OauthResource, AuthorityGroup> authorityGroupFetch = root.fetch(OauthResource_.authorityGroups, JoinType.LEFT);
		Fetch<AuthorityGroup, Authority> authorityFetch = authorityGroupFetch.fetch(AuthorityGroup_.authorities, JoinType.LEFT);
        query.select(root).distinct(true);
        TypedQuery<OauthResource> allQuery = entityManager.createQuery(query);
        return allQuery.getResultList();
	}

	@Override
	public void create(OauthResource oauthResource) {
		entityManager.persist(oauthResource);
	}

	@Override
	public OauthResource update(OauthResource oauthResource) {
		 return entityManager.merge(oauthResource);
	}

	@Override
	public void delete(String resourceId) {
		OauthResource oauthResource = entityManager.find(OauthResource.class, resourceId);
        delete(oauthResource);
	}

	@Override
	public void delete(OauthResource oauthResource) {
		entityManager.remove(oauthResource);
	}
}

