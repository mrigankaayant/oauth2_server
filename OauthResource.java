package com.ayantsoft.spring.security.oauth2.model.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OAUTH_RESOURCE")
@Getter
@Setter
@EqualsAndHashCode(of = "resourceId")
public class OauthResource implements Serializable {

	@Id
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "RESOURCE_NAME")
    private String resourceName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resourceId", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<AuthorityGroup> authorityGroups = new HashSet<>();

}
