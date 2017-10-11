/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbs.rp.gsam.gsamptp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author shoukat
 */
@Entity
public class PTP {
    @Id
    @GeneratedValue
    private long pid;
    private String ptpType;
    private String borowerName;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getPtpType() {
        return ptpType;
    }

    public void setPtpType(String ptpType) {
        this.ptpType = ptpType;
    }

    public String getBorowerName() {
        return borowerName;
    }

    public void setBorowerName(String borowerName) {
        this.borowerName = borowerName;
    }

    public PTP() {
    }

    public PTP(long pid, String ptpType, String borowerName) {
        this.pid = pid;
        this.ptpType = ptpType;
        this.borowerName = borowerName;
    }
    
    
    
}
