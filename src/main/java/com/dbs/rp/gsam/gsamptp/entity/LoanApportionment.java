/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbs.rp.gsam.gsamptp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author shoukat
 */
@Entity
public class LoanApportionment {

    public LoanApportionment() {
    }
    
    
    @Id
    @GeneratedValue
    private long ApportionmentId;
   
    private String loanType;
    private String loanAmount;
    private String loanApportionment;
    
    @ManyToOne
    @JoinColumn(name = "pid")
    private PTP ptp;
    
    public LoanApportionment(long ApportionmentId, String loanType, String loanAmount, String loanApportionment) {
        this.ApportionmentId = ApportionmentId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.loanApportionment = loanApportionment;
    }

    public long getApportionmentId() {
        return ApportionmentId;
    }

    public void setApportionmentId(long ApportionmentId) {
        this.ApportionmentId = ApportionmentId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanApportionment() {
        return loanApportionment;
    }

    public void setLoanApportionment(String loanApportionment) {
        this.loanApportionment = loanApportionment;
    }

    public PTP getPtp() {
        return ptp;
    }

    public void setPtp(PTP ptp) {
        this.ptp = ptp;
    }
    
    
}
