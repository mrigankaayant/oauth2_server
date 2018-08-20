package com.ayantsoft.spring.security.oauth2.model.security;

import org.springframework.security.core.GrantedAuthority;

import com.ayantsoft.spring.security.oauth2.model.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUTHORITY", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHORITY_GROUP_ID")
    @JsonBackReference
    private AuthorityGroup authorityGroup;

    @Override
    public String getAuthority() {
        return getName();
    }
}
