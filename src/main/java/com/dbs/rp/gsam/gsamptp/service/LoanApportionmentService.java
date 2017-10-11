/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbs.rp.gsam.gsamptp.service;

import com.dbs.rp.gsam.gsamptp.dao.ApportionmentRepository;
import com.dbs.rp.gsam.gsamptp.dao.PTPRepository;
//import com.dbs.rp.gsam.gsamptp.dao.GSAMPTPRepository;
import com.dbs.rp.gsam.gsamptp.entity.LoanApportionment;
import com.dbs.rp.gsam.gsamptp.entity.PTP;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shoukat
 */
@Service
public class LoanApportionmentService {
    
    
    @Autowired
    ApportionmentRepository gsamrepo;
    
    @Autowired
    PTPRepository ptprepo;
    
    public List<LoanApportionment> getLinkedApportionments (long ptpId){
        
        List<LoanApportionment> loanApportionments= new ArrayList();
        
       for (LoanApportionment apportionment : gsamrepo.findByPtpPid(ptpId) ){
           loanApportionments.add(apportionment);
       }
       
       System.out.println("Loan Apportionment : "+loanApportionments.toString());
       
       return (loanApportionments);
    }
    
    
    public List<PTP> createPTP(List<PTP> ptp){
        List<PTP> currPTPs = new ArrayList();
        
        for (PTP ptp1 : ptp ){
            
           System.out.println("PTP ID "+ptp1.getPid());
           ptprepo.save(ptp1); 
           currPTPs.add(ptp1);
       
        }
        
        System.out.println("Added PTPs Length ="+currPTPs.size());
        for (PTP currPTP: currPTPs){
           System.out.println(currPTP.getPtpType()); 
        }
       
        
       return currPTPs;
        
    }
    
    public List<LoanApportionment> addApportionment(List<LoanApportionment> apportions,Long pid){
        
        int count = 0;
        List<LoanApportionment> currApports = new ArrayList();
        for (LoanApportionment apportion : apportions ){
           apportion.setPtp(getPTP(pid));
           gsamrepo.save(apportion); 
           count ++;
           currApports.add(apportion);
        }
       
        return (currApports);
        //System.out.println("Apportionment added. Count= " +count);
    }

    public LoanApportionmentService(ApportionmentRepository gsamrepo, PTPRepository ptprepo) {
        this.gsamrepo = gsamrepo;
        this.ptprepo = ptprepo;
    }
    
    
    public PTP getPTP(Long ptpid){
        return ptprepo.findOne(ptpid);
    }
    
    
    public List<PTP> getAllPTP(){
        List<PTP> ptps = new ArrayList();
        for (PTP ptp : ptprepo.findAll()){
            ptps.add(ptp);
        }
        return ptps;
    }
    
}
