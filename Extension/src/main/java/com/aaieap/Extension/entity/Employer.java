package com.aaieap.Extension.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aaiPM")
    @JsonIgnore
    private Set<RecordMaintenance> maintenancesPM;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aaiOnsiteTechnician")
    @JsonIgnore
    private Set<RecordMaintenance> maintenancesTechnician;
    
    public Employer(String name) {
    	this.name = name;
    }
}
