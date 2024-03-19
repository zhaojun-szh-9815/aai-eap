package com.aaieap.Extension.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class IDCSite
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Excel(name = "IDC Site", width = 10)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcSite")
    @JsonIgnore
    private Set<RecordMaintenance> maintenancesIDCSite;
    
    public IDCSite(String name) {
    	this.name = name;
    }
}
