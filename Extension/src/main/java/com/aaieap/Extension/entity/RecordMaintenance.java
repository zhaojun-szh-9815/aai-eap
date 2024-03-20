package com.aaieap.Extension.entity;

import java.sql.Date;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordMaintenance
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel(name = "#", width = 10)
    private Long id;
    @Excel(name = "Task Name", width = 20)
    private String taskName;
    @Excel(name = "Task Description", width = 20)
    private String taskDescription;
    @Excel(name = "Ticket Info", width = 20)
    private String ticketInfo;
    @Excel(name = "Google Drive Sub Folder Name", width = 20)
    private String gdFolderName;
    @Excel(name = "Google Drive Folder Link", width = 10)
    private String gdFolderLink;
//    
//    @Transient
//    @Excel(name = "IDC Site", width = 10)
//    private String idcSiteName;
    
    @ManyToOne
    @JoinColumn(name = "idcSite", referencedColumnName = "id", nullable = false)
    @ExcelEntity
    private IDCSite idcSite;
    @Excel(name = "Quantity", width = 10)
    private Integer quantity;
    @Excel(name = "Device Specification", width = 10)
    private String deviceSpecification;
    @Excel(name = "Part Number", width = 10)
    private String partNumber;
    @Excel(name = "Defective Serial Number", width = 10)
    private String defectiveSerialNumber;
    @Excel(name = "Task Assigned Date", width = 10)
    private Date taskAssignedDate;
    @Excel(name = "Start Date", width = 10)
    private Date startDate;
    @Excel(name = "Complete Date", width = 10)
    private Date endDate;
    @Excel(name = "Client", width = 10)
    private String client;
    @Excel(name = "H3C PM", width = 10)
    private String clientPM;

    @ManyToOne
    @JoinColumn(name = "aaiPM", referencedColumnName = "id", nullable = false)
    private Employer aaiPM;

    @Transient
    @Excel(name = "AAI PM", width = 10)
    private String aaiPMName;

    @ManyToOne
    @JoinColumn(name = "aaiOnsiteTechnician", referencedColumnName = "id", nullable = false)
    private Employer aaiOnsiteTechnician;

    @Transient
    @Excel(name = "AAI onsite techinician", width = 10)
    private String aaiOnsiteTechnicianName;

    @Excel(name = "onsite working hours", width = 10)
    private Integer onsiteWorkingHours;
    @Excel(name = "Onsite technician dispatched", width = 10)
    private Integer onsiteTechnicianDispatched;
    @Excel(name = "Comments", width = 10)
    private String comments;
}
