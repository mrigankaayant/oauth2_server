package com.ayantsoft.spring.security.oauth2.model.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUTHORITY_GROUP")
@Getter
@Setter
public class AuthorityGroup implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "AUTHORITY_GROUP_NAME", nullable = false)
    private String authorityGroupName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESOURCE_ID")
    @JsonBackReference
    private OauthResource resourceId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorityGroup", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Authority> authorities = new HashSet<>();
}
